package com.epam.likarnya.web.command;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    private static final Logger logger = Logger.getLogger(CommandContainer.class);
    public static final Map<String, Command> commands;

    static {
        commands = new HashMap<>();
        commands.put("login", new GetLoginCommand());
//        commands.put("home", new CommandHome());
        commands.put("authorization", new PostLoginCommand());
        commands.put("adminCabinet", new AdminCabinet());
        commands.put("patient-form", new PatientFormRegistration());
        commands.put("medical-form", new MedicalFormRegistration());
        commands.put("patient-registration", new PostPatientRegistration());
        commands.put("medical-registration", new PostMedicalRegistration());
        commands.put("doctors", new DoctorsList());
        commands.put("nurses", new NursesList());
        commands.put("add-medical-card", new AddMedicalCard());
        commands.put("postCreateMedicCard", new PostCreateMedicalCard());
        commands.put("doctorCabinet", new DoctorCabinet());
        commands.put("logout", new LogoutCommand());
        commands.put("add-treatment", new TreatmentAssigning());
        commands.put("postTreatmentAssigning", new PostTreatmentAssigning());


        logger.debug("Command container was successfully initialized");
        logger.trace("Number of commands --> " + commands.size());
    }


    public static Command getCommand(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            logger.trace("Command not found, name --> " + commandName);
            return commands.get("commandNotFound");
        }
        return commands.get(commandName);
    }
}
