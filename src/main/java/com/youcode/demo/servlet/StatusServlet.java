package com.youcode.demo.servlet;

import com.youcode.demo.entity.Status;
import com.youcode.demo.service.StatusService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "StatusServlet", value = "/status")
public class StatusServlet extends HttpServlet {

    @Inject
    private StatusService statusService;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    private void insertStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String statusName = request.getParameter("status");

        Status status = new Status();
        status.setStatus(statusName);

        try {
            statusService.addStatus(status);
            response.sendRedirect(request.getContextPath() + "/status");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UUID id = UUID.fromString(request.getParameter("statusId"));
        String statusName = request.getParameter("status");

        Status updatedStatus = new Status();
        updatedStatus.setId(id);
        updatedStatus.setStatus(statusName);

        try {
            statusService.updateStatus(updatedStatus);
            response.sendRedirect(request.getContextPath() + "/status");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteStatus(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
        UUID id = UUID.fromString(request.getParameter("id"));
        boolean isDeleted = statusService.deleteStatus(id);
        if (isDeleted) {
            response.sendRedirect(request.getContextPath() + "/status");
        }
    }

    private void getStatus(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            try {
                UUID id = UUID.fromString(idParam);
                Status status = statusService.getStatusById(id);
                if (status != null) {
                    List<Status> statusList = new ArrayList<>();
                    statusList.add(status);
                    request.setAttribute("statusList", statusList);
                    request.getRequestDispatcher("/status.jsp").forward(request, response);
                } else {
                    request.setAttribute("message", "No Status Found With the Given Id.");
                    request.getRequestDispatcher("/status.jsp").forward(request, response);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("message", "Invalid Status Id.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void getStatuses(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Status> statuses = statusService.getAllStatuses();
        request.setAttribute("status", statuses);
        request.getRequestDispatcher("/admin/status.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getStatuses(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "insert":
                try {
                    insertStatus(request, response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "update":
                try {
                    updateStatus(request, response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "delete":
                try {
                    deleteStatus(request, response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "search":
                try {
                    getStatus(request, response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }
}
