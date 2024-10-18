package com.youcode.demo.servlet;

import com.youcode.demo.entity.RequestStatus;
import com.youcode.demo.entity.Status;
import com.youcode.demo.service.RequestService;
import com.youcode.demo.service.RequestStatusService;
import com.youcode.demo.service.StatusService;
import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "RequestStatusServlet", value = "/request-status")
public class RequestStatusServlet extends HttpServlet {

    @Inject
    private RequestStatusService requestStatusService;

    @Inject
    private RequestService requestService;



    @Override
    public void init() throws ServletException {
        super.init();
    }

    private void addRequestStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UUID requestId = UUID.fromString(request.getParameter("requestId"));
        UUID statusId = UUID.fromString(request.getParameter("statusId"));

        RequestStatus requestStatus = new RequestStatus();
        requestStatus.setRequest(requestService.getRequestById(requestId)); /
        Status status = new Status();
        status.setId(statusId);
        requestStatus.setStatus(status);

        requestStatusService.addRequestStatus(requestStatus);
        response.sendRedirect(request.getContextPath() + "/request-status");
    }

    private void updateRequestStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UUID requestId = UUID.fromString(request.getParameter("requestId"));
        UUID statusId = UUID.fromString(request.getParameter("statusId"));

        requestStatusService.updateRequestStatus(requestId, statusId);
        response.sendRedirect(request.getContextPath() +"/status");
    }

    /*private void deleteRequestStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UUID statusId = UUID.fromString(request.getParameter("statusId"));
        // Assuming you have a delete method in your service
        boolean isDeleted = requestStatusService.deleteRequestStatus(statusId);
        if (isDeleted) {
            response.sendRedirect(request.getContextPath() + "/request-status");
        }
    }

    private void getRequestStatuses(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<RequestStatus> requestStatuses = requestStatusService.getAllRequestStatuses(); // Method to fetch all statuses
        request.setAttribute("requestStatuses", requestStatuses);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/requestStatuses.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getRequestStatuses(request, response);
    }*/

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "add":
                try {
                    addRequestStatus(request, response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "update":
                try {
                    updateRequestStatus(request, response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "delete":
                try {
                    deleteRequestStatus(request, response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }
}
