package com.example.demo

import com.vaadin.flow.server.VaadinServlet
import com.vaadin.flow.server.VaadinServletConfiguration

import javax.servlet.annotation.WebServlet


@WebServlet(urlPatterns = ["/*"], name = "DemoServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = DemoUI::class, productionMode = true)
class DemoServlet : VaadinServlet()