package com.excilys.util;


/**
 * Pattern command for the processing of actions.
 */
public enum Command {
//    /**
//     * Retrieve all computers.
//     */
//    GET_ALL_COMPUTERS("getAllComputers", "Retrieve all computers") {
//        @Override
//        public void execute(ComputerDatabaseContext ctx)
//                throws ServiceException {
//            if (ctx == null) {
//                throw new IllegalArgumentException();
//            }
//            ctx.setComputers(ComputerService.INSTANCE.getAll());
//            System.out.println(ctx.getComputers());
//        }
//
//    },
//    /**
//     * Retrieve all companies.
//     */
//    GET_ALL_COMPANIES("getAllCompanies", "Retrieve all companies") {
//        @Override
//        public void execute(ComputerDatabaseContext ctx)
//                throws ServiceException {
//            if (ctx == null) {
//                throw new IllegalArgumentException();
//            }
//            ctx.setCompanies(CompanyService.INSTANCE.getAll());
//            System.out.println(ctx.getCompanies());
//        }
//
//    },
//    /**
//     * Retrieve a computer.
//     */
//    GET_BY_ID_COMPUTER("getByIdComputer", "Retrieve one computer") {
//        @Override
//        public void execute(ComputerDatabaseContext ctx)
//                throws ServiceException {
//            if (ctx == null) {
//                throw new IllegalArgumentException();
//            }
//            System.out.print("Identifier : ");
//            ctx.setComputerId(Long.valueOf(ctx.getScanner().getNextToken()));
//            ctx.setComputers(Arrays.asList(ComputerService.INSTANCE.getById(ctx
//                    .getComputerId())));
//            System.out.println(ctx.getComputers());
//        }
//
//    },
//    /**
//     * Create a new computer.
//     */
//    CREATE_COMPUTER("createComputer", "Create a new computer") {
//        @Override
//        public void execute(ComputerDatabaseContext ctx)
//                throws ServiceException {
//            if (ctx == null) {
//                throw new IllegalArgumentException();
//            }
//            final Computer computer = new Computer();
//            populate(ctx, computer);
//            ctx.setNewComputer(computer);
//            ComputerService.INSTANCE.create(ctx.getNewComputer());
//            if (computer.getId() > 0) {
//                System.out.println("Successfully created");
//            } else {
//                System.out.println("Failed to create");
//            }
//        }
//
//    },
//    /**
//     * Update a computer.
//     */
//    UPDATE_COMPUTER("updateComputer", "Update a computer") {
//        @Override
//        public void execute(ComputerDatabaseContext ctx)
//                throws ServiceException {
//            if (ctx == null) {
//                throw new IllegalArgumentException();
//            }
//            System.out.println("Identifier : ");
//            final Computer computer = ComputerService.INSTANCE.getById(Long
//                    .valueOf(ctx.getScanner().getNextToken()));
//            populate(ctx, computer);
//            ctx.setNewComputer(computer);
//            ComputerService.INSTANCE.update(ctx.getNewComputer());
//            System.out.println("UPDATED");
//        }
//
//    },
//    /**
//     * Delete a computer.
//     */
//    DELETE_COMPUTER("deleteComputer", "Delete a computer") {
//        @Override
//        public void execute(ComputerDatabaseContext ctx)
//                throws ServiceException {
//            if (ctx == null) {
//                throw new IllegalArgumentException();
//            }
//            System.out.println("Identifier : ");
//            ctx.setComputerId(Long.valueOf(ctx.getScanner().getNextToken()));
//            ComputerService.INSTANCE.delete(ctx.getComputerId());
//            System.out.println("Deleted");
//        }
//
//    },
//    /**
//     * Command to display help.
//     */
//    HELP("help", "Display this help") {
//        @Override
//        public void execute(ComputerDatabaseContext ctx) {
//            if (ctx == null) {
//                throw new IllegalArgumentException();
//            }
//            if (ctx.getHelp() == null) {
//                final StringBuilder sb = new StringBuilder();
//                sb.append("==Commands==\n\n");
//                for (Command c : commands.values()) {
//                    sb.append(c.toString()).append(" ").append("[").append(c.info)
//                            .append("]").append("\n");
//                }
//                ctx.setHelp(sb.toString());
//            }
//            System.out.println(ctx.getHelp());
//        }
//
//    },
//    /**
//     * Command to terminate a program.
//     */
//    EXIT("exit", "Stop the program") {
//        @Override
//        public void execute(ComputerDatabaseContext ctx)
//                throws ServiceException {
//            if (ctx == null) {
//                throw new IllegalArgumentException();
//            }
//            ctx.getScanner().exit();
//        }
//
//    };
//
//    private static Map<String, Command> commands;
//
//    static {
//        commands = new HashMap<>();
//        for (Command com : Command.values()) {
//            commands.put(com.commandLabel, com);
//        }
//    }
//
//    private final String commandLabel;
//    private final String info;
//
//    private Command(String commandLabel, String info) {
//        this.commandLabel = commandLabel;
//        this.info = info;
//    }
//
//    /*
//     * Populate a computer model from answers given by the user.
//     */
//    private static void populate(ComputerDatabaseContext ctx, Computer computer) {
//        System.out.println("Name : ");
//        if (ctx.getScanner().hasNextToken()) {
//            computer.setName(ctx.getScanner().getNextToken());
//        }
//        System.out.println("Introduced : ");
//        if (ctx.getScanner().hasNextToken()) {
//            final String tok = ctx.getScanner().getNextToken();
//            final StringBuilder sb = new StringBuilder();
//            sb.append(tok).append(" ").append("00:00:00");
//            if (ComputerDatabaseValidator.INSTANCE.validateDateTime(sb.toString())) {
//                DateTimeFormatter formatter = DateTimeFormatter
//                        .ofPattern("yyyy-MM-dd HH:mm:ss");
//                LocalDateTime dateTime = LocalDateTime.parse(sb.toString(),
//                        formatter);
//                computer.setIntroduced(dateTime);
//            }
//        }
//        System.out.println("Discontinued : ");
//        if (ctx.getScanner().hasNextToken()) {
//            final String tok = ctx.getScanner().getNextToken();
//            final StringBuilder sb = new StringBuilder();
//            sb.append(tok).append(" ").append("00:00:00");
//            if (ComputerDatabaseValidator.INSTANCE.validateDateTime(sb.toString())) {
//                DateTimeFormatter formatter = DateTimeFormatter
//                        .ofPattern("yyyy-MM-dd HH:mm:ss");
//                LocalDateTime dateTime = LocalDateTime.parse(sb.toString(),
//                        formatter);
//                computer.setDiscontinued(dateTime);
//            }
//        }
//        System.out.println("Company id : ");
//        if (ctx.getScanner().hasNextToken()) {
//            computer.setCompany(CompanyService.INSTANCE.getById(Long
//                    .valueOf(ctx.getScanner().getNextToken())));
//        }
//    }
//
//    /**
//     * Return a command from its textual value.
//     *
//     * @param command Textual command
//     * @return The matching command (or HELP if wrong command)
//     */
//    public static Command getCommand(String command) {
//        final Command com = commands.get(command);
//        return (com == null) ? Command.HELP : com;
//    }
//
//    public abstract void execute(ComputerDatabaseContext ctx);
//
//    @Override
//    public String toString() {
//        return commandLabel;
//    }
}
