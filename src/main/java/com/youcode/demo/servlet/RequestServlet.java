package com.youcode.demo.servlet;

import com.youcode.demo.enums.Title;
import com.youcode.demo.entity.Request;
import com.youcode.demo.service.RequestService;
import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "RequestServlet", value = "/requests")
public class RequestServlet extends HttpServlet {

    @Inject
    private RequestService requestService;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    private void insertRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String project = request.getParameter("project");
        String occupation =request.getParameter("occupation");
        Double amount = Double.parseDouble(request.getParameter("amount"));
        Integer period = Integer.parseInt(request.getParameter("period"));
        Double monthlyPayment =  Double.parseDouble(request.getParameter("monthlyPayment"));
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        Title title = Title.valueOf(request.getParameter("title"));
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String idCard = request.getParameter("idCard");
        LocalDate birthdate = LocalDate.parse(request.getParameter("birthdate"));
        LocalDate hiringDate = LocalDate.parse(request.getParameter("hiringDate"));
        Double monthlyIncome = Double.valueOf(request.getParameter("monthlyIncome"));
        Boolean oldLoan = Boolean.parseBoolean(request.getParameter("oldLoan"));

        Request request1 = new Request();
        request1.setProject(project);
        request1.setOccupation(occupation);
        request1.setAmount(amount);
        request1.setPeriod(period);
        request1.setMonthlyPayment(monthlyPayment);
        request1.setEmail(email);
        request1.setPhone(phone);
        request1.setTitle(title);
        request1.setName(name);
        request1.setLastName(lastName);
        request1.setIdCard(idCard);
        request1.setBirthdate(birthdate);
        request1.setHiringDate(hiringDate);
        request1.setMonthlyIncome(monthlyIncome);
        request1.setOldLoan(oldLoan);

        try{
            requestService.addRequest(request1);
            response.sendRedirect(request.getContextPath() + "/requests");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void updateRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UUID id = UUID.fromString(request.getParameter("id"));
        String project = request.getParameter("project");
        String occupation =request.getParameter("occupation");
        Double amount = Double.parseDouble(request.getParameter("amount"));
        Integer period = Integer.parseInt(request.getParameter("period"));
        Double monthlyPayment =  Double.parseDouble(request.getParameter("monthlyPayment"));
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        Title title = Title.valueOf(request.getParameter("title"));
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String idCard = request.getParameter("idCard");
        LocalDate birthdate = LocalDate.parse(request.getParameter("birthdate"));
        LocalDate hiringDate = LocalDate.parse(request.getParameter("hiringDate"));
        Double monthlyIncome = Double.valueOf(request.getParameter("monthlyIncome"));
        Boolean oldLoan = Boolean.parseBoolean(request.getParameter("oldLoan"));

        Request updatedRequest = new Request();
        updatedRequest.setProject(project);
        updatedRequest.setOccupation(occupation);
        updatedRequest.setAmount(amount);
        updatedRequest.setPeriod(period);
        updatedRequest.setMonthlyPayment(monthlyPayment);
        updatedRequest.setEmail(email);
        updatedRequest.setPhone(phone);
        updatedRequest.setTitle(title);
        updatedRequest.setName(name);
        updatedRequest.setLastName(lastName);
        updatedRequest.setIdCard(idCard);
        updatedRequest.setBirthdate(birthdate);
        updatedRequest.setHiringDate(hiringDate);
        updatedRequest.setMonthlyIncome(monthlyIncome);
        updatedRequest.setOldLoan(oldLoan);

        try{
            requestService.updateRequest(updatedRequest);
            response.sendRedirect(request.getContextPath() + "/requests");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void deleteRequest(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
        UUID id = UUID.fromString(request.getParameter("id"));
        boolean isDeleted = requestService.deleteRequest(id);
        if(isDeleted){
            response.sendRedirect(request.getContextPath() + "/requests");
        }
    }

    private void getRequest(HttpServletRequest request, HttpServletResponse response, String requestId) throws Exception, IOException {
        Request requestDetails = requestService.getRequestById(UUID.fromString(requestId));
        request.setAttribute("singleRequest", requestDetails);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/singleRequest.jsp");
        dispatcher.forward(request, response);
    }

    private void getRequests (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Request> requests = requestService.getAllRequests();
        request.setAttribute("requests", requests);
        request.getRequestDispatcher("/admin/requests.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestId = request.getParameter("requestId");
        if (requestId != null && !requestId.isEmpty()) {
            try {
                getRequest(request, response, requestId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            getRequests(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "insert":
                try {
                    insertRequest(request, response);
                }catch (Exception e){
                    throw new RuntimeException(e);
                }
                break;
            case "update":
                try{
                    updateRequest(request, response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "delete":
                try{
                    deleteRequest(request, response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
           /* case "search":
                try {
                    getRequest(request, response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;*/
        }
    }
}

