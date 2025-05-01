package com.example.pendu;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/pendu")
public class PenduServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        // Initialiser un nouveau jeu si nécessaire
        PenduGame game = (PenduGame) session.getAttribute("game");
        if (game == null) {
            game = new PenduGame();
            session.setAttribute("game", game);
        }
        
        // Vérifier si le jeu est terminé
        if (game.isGameOver()) {
            response.sendRedirect("result.jsp");
            return;
        }
        
        request.getRequestDispatcher("game.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        PenduGame game = (PenduGame) session.getAttribute("game");
        
        if (game == null) {
            response.sendRedirect("index.jsp");
            return;
        }
        
        String letterParam = request.getParameter("letter");
        if (letterParam != null && !letterParam.isEmpty()) {
            char letter = letterParam.charAt(0);
            if (Character.isLetter(letter)) {
                game.tryLetter(letter);
            }
        }
        
        // Redémarrer le jeu si demandé
        if (request.getParameter("restart") != null) {
            session.removeAttribute("game");
        }
        
        response.sendRedirect("pendu");
    }
}