package com.youcode.demo.servlet;

import com.youcode.demo.entity.RequestStatus;
import com.youcode.demo.entity.Status;
import com.youcode.demo.service.RequestStatusService;
import com.youcode.demo.service.StatusService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "RequestStatusServlet", value = "/requestStatus")
public class RequestStatusServlet extends HttpServlet {

    @Inject
    private RequestStatusService requestStatusService;

    @Inject
    private StatusService statusService;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    private void updateRequestStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UUID requestStatusId = UUID.fromString(request.getParameter("requestStatusId"));
        String newStatus = request.getParameter("status");

        RequestStatus requestStatus = requestStatusService.getRequestStatusById(requestStatusId);
        if (requestStatus != null) {
            Status foundStatus = statusService.getStatusByStatusName(newStatus);
            requestStatus.setRequest(requestStatus.getRequest());
            requestStatus.setStatus(foundStatus);
            requestStatus.setReason(requestStatus.getReason());
            requestStatusService.updateRequestStatus(requestStatusId, requestStatus);
        }

        response.sendRedirect(request.getContextPath() + "/requests");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.equals("update")) {
            try {
                updateRequestStatus(request, response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}