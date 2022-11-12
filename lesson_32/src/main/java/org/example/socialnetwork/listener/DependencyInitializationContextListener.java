package org.example.socialnetwork.listener;


import org.example.socialnetwork.service.UserRequestService;
import org.example.socialnetwork.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DependencyInitializationContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(final ServletContextEvent sce) {
        final ApplicationContext context = new ClassPathXmlApplicationContext("lesson_32.xml");
        final UserService userService = context.getBean(UserService.class);
        sce.getServletContext().setAttribute("userService", userService);
        final UserRequestService userRequestService = context.getBean(UserRequestService.class);
        sce.getServletContext().setAttribute("userRequestService", userRequestService);
    }
}
