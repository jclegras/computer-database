package com.excilys.controller;

import com.excilys.dto.CompanyDTO;
import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.MapperDTO;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.persistence.util.Field;
import com.excilys.persistence.util.Page;
import com.excilys.persistence.util.SimplePage;
import com.excilys.service.ICompanyService;
import com.excilys.service.IComputerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes("companies")
public class ComputerController {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(ComputerController.class);
    private static final String DASHBOARD_VIEW = "dashboard";
    private static final String ADDCOMPUTER_VIEW = "addComputer";
    private static final String EDIT_VIEW = "editComputer";
    private static final String PAGE_404 = "404";
    @Autowired
    private MapperDTO<Computer, ComputerDTO> computerMapperDTO;
    @Autowired
    private MapperDTO<Company, CompanyDTO> companyMapperDTO;
    @Autowired
    private IComputerService computerService;
    @Autowired
    private ICompanyService companyService;

    @ModelAttribute("companies")
    public List<CompanyDTO> populateModelWithCompanies() {
        return companyMapperDTO.modelsToDto(companyService.getAll());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/dashboard")
    public String getAll(@RequestParam("page") Optional<Integer> page,
                         @RequestParam("size") Optional<Integer> size,
                         @RequestParam("search") Optional<String> search,
                         @RequestParam("sort") Optional<String> sort,
                         @RequestParam("col") Optional<String> column, Model model) {
        final Page p = SimplePage.builder().build();
        page.ifPresent(x -> p.setPage(page.get()));
        page.ifPresent(x -> p.setSize(size.get()));
        if (search.isPresent()) {
            final String trimmedSearch = search.get().trim();
            p.setTotalEntities(computerService.count(trimmedSearch));
            long maxPages = (p.getTotalEntities() / p.getSize());
            if (p.getTotalEntities() % p.getSize() != 0) {
                ++maxPages;
            }
            p.setTotalPages(maxPages);
            p.setDisplayablePages(Math.min(maxPages, p.getPage() + p.getSize() - 1));
            model.addAttribute("page", p);
            model.addAttribute("computers",
                    computerMapperDTO.modelsToDto(computerService.getByName(p, trimmedSearch)));
            model.addAttribute("lastSearch", trimmedSearch);
            return DASHBOARD_VIEW;
        }
        if (sort.isPresent()) {
            final String srt = sort.get().trim();
            if (!srt.isEmpty()) {
                if (Page.Sort.isValid(srt)) {
                    p.setSort(Page.Sort.valueOf(srt));
                } else {
                    return PAGE_404;
                }
            }
        }
        if (column.isPresent()) {
            final String col = column.get().trim();
            if (!Field.isValid(col)) {
                return PAGE_404;
            }
            if (!col.isEmpty()) {
                p.setProperties(col);
            }
        }
        p.setTotalEntities(computerService.count());
        long maxPages = (p.getTotalEntities() / p.getSize());
        if (p.getTotalEntities() % p.getSize() != 0) {
            ++maxPages;
        }
        p.setTotalPages(maxPages);
        p.setDisplayablePages(Math.min(maxPages, p.getPage() + p.getSize() - 1));
        model.addAttribute("page", p);
        model.addAttribute("computers",
                computerMapperDTO.modelsToDto(computerService.getAll(p)));
        return DASHBOARD_VIEW;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/addComputer")
    public String add(@ModelAttribute("newComputer") ComputerDTO computerDTO,
                      Model model, HttpServletRequest request) {
        model.addAttribute("prev", request.getHeader("Referer"));
        return ADDCOMPUTER_VIEW;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addComputer")
    public String add(
            @Valid @ModelAttribute("newComputer") ComputerDTO computerDTO,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", true);
            return ADDCOMPUTER_VIEW;
        }
        populateCompany(computerDTO);
        final Computer newComputer = computerMapperDTO.dtoToModel(computerDTO);
        computerService.create(newComputer);
        LOGGER.info("Successfully created computer with id {}",
                newComputer.getId());
        return "redirect:" + DASHBOARD_VIEW;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/editComputer")
    public String edit(@RequestParam("id") Optional<Long> id, Model model, HttpServletRequest request) {
        model.addAttribute("prev", request.getHeader("Referer"));
        if (id.isPresent()) {
            if (id.get() <= 0) {
                return PAGE_404;
            } else {
                model.addAttribute("computer", computerMapperDTO
                        .modelToDto(computerService.getById(id.get())));
            }
        } else {
            return PAGE_404;
        }
        return EDIT_VIEW;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/editComputer")
    public String edit(
            @Valid @ModelAttribute("computer") ComputerDTO computerDTO,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", true);
            return EDIT_VIEW;
        }
        populateCompany(computerDTO);
        final Computer computer = computerMapperDTO.dtoToModel(computerDTO);
        computerService.update(computer);
        LOGGER.info("Successfully updated computer with id {}",
                computer.getId());
        return "redirect:" + DASHBOARD_VIEW;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public String delete(@RequestParam("selection") Long[] ids, Model model) {
        for (Long id : ids) {
            computerService.delete(id);
            LOGGER.info("Successfully deleted computer with id {}", id);
        }
        model.addAttribute("computers",
                computerMapperDTO.modelsToDto(computerService.getAll()));
        return "redirect:" + DASHBOARD_VIEW;
    }

    /*
     * Populate company from computerDTO's companyId
     */
    private void populateCompany(ComputerDTO computerDTO) {
        if (computerDTO.getCompanyId() != null) {
            computerDTO.setCompanyName(companyService.getById(
                    Long.valueOf(computerDTO.getCompanyId())).getName());
        }
    }
}
