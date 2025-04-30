package example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/HangmanServlet")
public class HangmanServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        // Initialize a new game if none exists
        if (session.getAttribute("game") == null) {
            session.setAttribute("game", new HangmanGame());
        }
        
        request.getRequestDispatcher("game.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        HangmanGame game = (HangmanGame) session.getAttribute("game");
        
        String letterParam = request.getParameter("letter");
        if (letterParam != null && !letterParam.isEmpty()) {
            char letter = letterParam.toUpperCase().charAt(0);
            game.guessLetter(letter);
        }
        
        if (game.isGameOver()) {
            request.getRequestDispatcher("result.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("game.jsp").forward(request, response);
        }
    }
}