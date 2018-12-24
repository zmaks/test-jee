package com.interview.practice.jee.atm.controller;

import com.interview.practice.jee.atm.CardInfoDto;
import com.interview.practice.jee.atm.exception.CardLoginAttemptsExhaustedException;
import com.interview.practice.jee.atm.exception.IncorrectCardCredentialsException;
import com.interview.practice.jee.atm.service.CardService;
import com.interview.practice.jee.atm.service.provider.ServiceFactory;
import com.interview.practice.jee.atm.service.provider.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CardLoginServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(CardLoginServlet.class.getName());


    private static final String CARD_LOGIN_ATTEMPTS_ATTR = "card_login_attempts";

    private final CardService cardService;

    public CardLoginServlet() {
        this(ServiceFactoryImpl.getInstance());
    }

    public CardLoginServlet(ServiceFactory serviceFactory) {
        cardService = serviceFactory.get(CardService.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Integer attemptsCount = 0;
            HttpSession session = req.getSession(true);
            synchronized (session) {
                Object attempts = session.getAttribute(CARD_LOGIN_ATTEMPTS_ATTR);
                if (attempts != null) {
                    attemptsCount = (Integer) attempts;
                }
                attemptsCount++;
                session.setAttribute(CARD_LOGIN_ATTEMPTS_ATTR, attemptsCount);
            }


            Long cardNumber = Long.parseLong(req.getParameter("cardNumber"));
            int pin = Integer.parseInt(req.getParameter("pin"));

            CardInfoDto cardInfo = cardService.getCardInfo(cardNumber, pin, attemptsCount);
            session.removeAttribute(CARD_LOGIN_ATTEMPTS_ATTR);
            req.getServletContext().setAttribute("cardInfo", cardInfo);
            //req.getRequestDispatcher("/account.jsp").forward(req, resp);

            resp.sendRedirect("/account.jsp");
        } catch (NumberFormatException e) {
            handleError(req, resp, "Incorrect input data");
        } catch (IncorrectCardCredentialsException | CardLoginAttemptsExhaustedException e) {
            handleError(req, resp, e.getMessage());
        }  catch (Exception e) {
            handleError(req, resp,"Internal Server Error");
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void handleError(HttpServletRequest req, HttpServletResponse resp, String message) throws ServletException, IOException {
        req.setAttribute("message", message);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameter("cardNumber"));
        System.out.println(req.getParameter("pin"));
    }
}
