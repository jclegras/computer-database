package com.excilys.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.MapperDTO;
import com.excilys.model.Computer;
import com.excilys.service.IComputerService;
import com.excilys.util.Page;
import com.excilys.util.SimplePage;

@Controller
@RequestMapping("/dashboard")
public class Dashboard {
	private static final String DASHBOARD_VIEW = "dashboard";
	@Autowired
    private MapperDTO<Computer, ComputerDTO> computerMapperDTO;
    @Autowired
    private IComputerService computerService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String dashboard(@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size,
			@RequestParam("search") Optional<String> search,
			@RequestParam("sort") Optional<String> sort,
			@RequestParam("col") Optional<String> column,
			Model model) {
		final Page p = new SimplePage("computer.name");
        if (search.isPresent()) {
            List<Computer> computers = computerService.getByName(search.get().trim());
            p.setTotalEntities(computers.size());
            model.addAttribute("computers", 
            		computerMapperDTO.modelsToDto(computers));
            return DASHBOARD_VIEW;
        }
        page.ifPresent(x -> p.setPage(page.get()));
        page.ifPresent(x -> p.setSize(size.get()));
        if (sort.isPresent()) {
        	final String srt = sort.get().trim();
            if (!srt.isEmpty()) {
                if (Page.Sort.isValid(srt)) {
                    p.setSort(Page.Sort.valueOf(srt));
                }
            }
        }
        if (column.isPresent()) {
        	final String col = column.get().trim();
            if (!col.isEmpty()) {
                p.setProperties(col);
            }
        }
        p.setTotalEntities(computerService.count());
        int maxPages = (p.getTotalEntities() / p.getSize());
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
	
}
