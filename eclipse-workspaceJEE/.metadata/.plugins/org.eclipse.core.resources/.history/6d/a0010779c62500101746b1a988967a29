<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="example.HangmanGame" %>
<%
    HangmanGame game = (HangmanGame) session.getAttribute("game");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dark Comedy Hangman</title>
    <style>
        body {
            font-family: 'Creepster', cursive, sans-serif;
            background-color: #121212;
            color: #e0e0e0;
            text-align: center;
            margin: 0;
            padding: 20px;
            background-image: url('https://assets.codepen.io/215128/crime-scene-bg.jpg');
            background-size: cover;
        }
        
        @import url('https://fonts.googleapis.com/css2?family=Creepster&display=swap');
        
        .game-container {
            max-width: 800px;
            margin: 0 auto;
            background-color: rgba(34, 34, 34, 0.9);
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(255, 0, 0, 0.5);
            border: 3px solid #ff4444;
            position: relative;
            overflow: hidden;
        }
        
        .game-container:before {
            content: "";
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background: url('https://assets.codepen.io/215128/forensic-tape.png');
            background-size: 100% auto;
            opacity: 0.15;
            pointer-events: none;
        }
        
        h1 {
            color: #ff4444;
            text-shadow: 0 0 10px rgba(255, 0, 0, 0.7);
            font-size: 3em;
            margin-bottom: 10px;
            letter-spacing: 3px;
        }
        
        .subtitle {
            color: #aaa;
            font-style: italic;
            margin-bottom: 20px;
        }
        
        .scene {
            margin: 20px auto;
            position: relative;
            height: 350px;
            background-color: #333;
            border-radius: 5px;
            overflow: hidden;
            border: 2px solid #ff4444;
            box-shadow: inset 0 0 20px rgba(0, 0, 0, 0.5);
        }
        
        .floor {
            position: absolute;
            bottom: 0;
            width: 100%;
            height: 20px;
            background-color: #555;
        }
        
        .word-display {
            font-size: 32px;
            letter-spacing: 10px;
            margin: 30px 0;
            color: #fff;
            text-shadow: 0 0 5px rgba(255, 255, 255, 0.3);
            font-family: 'Courier New', monospace;
            font-weight: bold;
            padding: 15px;
            background-color: rgba(0, 0, 0, 0.5);
            border-radius: 5px;
            border: 1px solid #ff4444;
        }
        
        .attempts {
            color: #ff4444;
            font-weight: bold;
            font-size: 20px;
            margin: 20px 0;
        }
        
        .guess-form {
            margin: 20px 0;
        }
        
        .guess-input {
            padding: 12px;
            font-size: 20px;
            width: 50px;
            text-align: center;
            background-color: #333;
            color: white;
            border: 2px solid #ff4444;
            border-radius: 5px;
            font-family: 'Creepster', cursive;
            margin-right: 10px;
        }
        
        .guess-button {
            padding: 12px 25px;
            font-size: 18px;
            background-color: #ff4444;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: all 0.3s;
            font-family: 'Creepster', cursive;
            letter-spacing: 1px;
        }
        
        .guess-button:hover {
            background-color: #ff0000;
            transform: scale(1.05);
            box-shadow: 0 0 15px rgba(255, 0, 0, 0.7);
        }
        
        .used-letters {
            margin-top: 20px;
            color: #aaa;
            font-style: italic;
            font-size: 18px;
        }
        
        /* Animations */
        @keyframes shake {
            0%, 100% { transform: translateX(0); }
            25% { transform: translateX(-10px); }
            75% { transform: translateX(10px); }
        }
        
        @keyframes pistolShot {
            0% { transform: translateX(0) translateY(0); }
            50% { transform: translateX(10px) translateY(-10px); }
            100% { transform: translateX(0) translateY(0); }
        }
        
        @keyframes bloodSplatter {
            0% { opacity: 0; transform: scale(0); }
            50% { opacity: 1; transform: scale(1); }
            100% { opacity: 0; transform: scale(1.5); }
        }
        
        .shake {
            animation: shake 0.5s ease-in-out;
        }
        
        .shot {
            animation: pistolShot 0.3s ease-in-out;
        }
        
        .blood {
            position: absolute;
            width: 100px;
            height: 100px;
            background-image: url('data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 100 100"><path d="M30,50 Q50,30 70,50 Q60,70 50,60 Q40,70 30,50" fill="rgba(200,0,0,0.7)"/></svg>');
            background-repeat: no-repeat;
            opacity: 0;
            z-index: 10;
        }
        
        /* Character styles */
        .victim {
            position: absolute;
            bottom: 20px;
            left: 35%;
            width: 150px;
            height: 250px;
            z-index: 2;
            transition: all 0.3s;
        }
        
        .executioner {
            position: absolute;
            bottom: 20px;
            right: 50px;
            width: 180px;
            height: 300px;
            z-index: 1;
        }
        
        .character svg {
            width: 100%;
            height: 100%;
        }
        
        .bullet-hole {
            position: absolute;
            width: 20px;
            height: 20px;
            background-color: #000;
            border-radius: 50%;
            border: 2px solid #800;
            display: none;
        }
        
        .smoke {
            position: absolute;
            width: 40px;
            height: 40px;
            background-image: url('data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 100 100"><circle cx="50" cy="50" r="40" fill="rgba(200,200,200,0.5)"/></svg>');
            background-repeat: no-repeat;
            opacity: 0;
            z-index: 5;
        }
    </style>
</head>
<body>
    <div class="game-container">
        <h1>CRIME SCENE HANGMAN</h1>
        <p class="subtitle">Guess the word or watch the executioner have his fun...</p>
        
        <div class="scene">
            <div class="floor"></div>
            
            <!-- Victim Character -->
            <div class="victim character" id="victim">
                <svg viewBox="0 0 200 300" xmlns="http://www.w3.org/2000/svg">
                    <!-- Body -->
                    <rect x="70" y="120" width="60" height="120" fill="#5d4037" rx="5"/>
                    <!-- Head -->
                    <circle cx="100" cy="80" r="40" fill="#ffdbac"/>
                    <!-- Face -->
                    <circle cx="85" cy="70" r="5" fill="#000"/>
                    <circle cx="115" cy="70" r="5" fill="#000"/>
                    <path d="M85 95 Q100 105 115 95" stroke="#000" fill="none" stroke-width="2"/>
                    <!-- Arms -->
                    <rect x="30" y="120" width="40" height="15" fill="#5d4037" rx="5"/>
                    <rect x="130" y="120" width="40" height="15" fill="#5d4037" rx="5"/>
                    <!-- Legs -->
                    <rect x="70" y="240" width="25" height="50" fill="#3e2723" rx="3"/>
                    <rect x="105" y="240" width="25" height="50" fill="#3e2723" rx="3"/>
                    <!-- Sweat drops when scared -->
                    <circle cx="90" cy="60" r="3" fill="#88f" class="sweat" style="opacity: 0;"/>
                    <circle cx="110" cy="60" r="3" fill="#88f" class="sweat" style="opacity: 0;"/>
                </svg>
            </div>
            
            <!-- Executioner Character -->
            <div class="executioner character" id="executioner">
                <svg viewBox="0 0 200 300" xmlns="http://www.w3.org/2000/svg">
                    <!-- Body -->
                    <rect x="70" y="100" width="60" height="150" fill="#333" rx="5"/>
                    <!-- Head -->
                    <circle cx="100" cy="60" r="40" fill="#444"/>
                    <!-- Mask -->
                    <circle cx="100" cy="70" r="30" fill="#000"/>
                    <!-- Eye holes -->
                    <circle cx="85" cy="60" r="8" fill="#fff"/>
                    <circle cx="115" cy="60" r="8" fill="#fff"/>
                    <!-- Arms -->
                    <rect x="30" y="120" width="40" height="15" fill="#222" rx="5"/>
                    <rect x="130" y="120" width="40" height="15" fill="#222" rx="5"/>
                    <!-- Pistol -->
                    <rect x="165" y="130" width="50" height="15" fill="#777" rx="3" id="pistol"/>
                    <rect x="215" y="135" width="10" height="5" fill="#555"/>
                    <!-- Legs -->
                    <rect x="70" y="250" width="25" height="40" fill="#222" rx="3"/>
                    <rect x="105" y="250" width="25" height="40" fill="#222" rx="3"/>
                </svg>
            </div>
            
            <!-- Effects -->
            <div class="blood" id="blood"></div>
            <div class="bullet-hole" id="bullet-hole"></div>
            <div class="smoke" id="smoke"></div>
        </div>
        
        <div class="word-display">
            <%= game.getDisplayWord() %>
        </div>
        
        <div class="attempts">
            Wrong attempts left: <span id="remaining-attempts"><%= game.getRemainingAttempts() %></span> 
            <span id="attemptMessage"></span>
        </div>
        
        <form class="guess-form" action="HangmanServlet" method="post">
            <input type="text" class="guess-input" name="letter" maxlength="1" required 
                   pattern="[A-Za-z]" title="Please enter a single letter">
            <input type="submit" class="guess-button" value="GUESS">
        </form>
        
        <div class="used-letters">
            Used letters: 
            <% for (char c : game.getGuessedLetters()) { %>
                <%= c %> 
            <% } %>
        </div>
    </div>
    
    <script>
        // Focus the input field on page load
        document.querySelector('.guess-input').focus();
        
        // Convert input to uppercase automatically
        document.querySelector('.guess-input').addEventListener('input', function(e) {
            this.value = this.value.toUpperCase();
        });
        
        // Animation for wrong guesses
        <% if (!game.getGuessedLetters().isEmpty() && 
              game.getWordToGuess().indexOf(game.getGuessedLetters().get(game.getGuessedLetters().size()-1)) < 0) { %>
            const remainingAttempts = <%= game.getRemainingAttempts() %>;
            const totalAttempts = <%= HangmanGame.MAX_ATTEMPTS %>;
            
            // Update attempt counter
            document.getElementById('remaining-attempts').textContent = remainingAttempts;
            
            // Shake victim and show sweat
            document.getElementById('victim').classList.add('shake');
            document.querySelectorAll('.sweat').forEach(sweat => {
                sweat.style.opacity = '1';
                setTimeout(() => { sweat.style.opacity = '0'; }, 1000);
            });
            
            // Pistol animation
            document.getElementById('pistol').classList.add('shot');
            
            // Blood splatter effect for the last attempt
            if (remainingAttempts === 0) {
                const blood = document.getElementById('blood');
                blood.style.left = '35%';
                blood.style.top = '30%';
                blood.style.animation = 'bloodSplatter 1s forwards';
                setTimeout(() => { blood.style.opacity = '0'; }, 1000);
                
                // Add bullet hole
                const bulletHole = document.getElementById('bullet-hole');
                bulletHole.style.display = 'block';
                bulletHole.style.left = '38%';
                bulletHole.style.top = '35%';
                
                // Add smoke
                const smoke = document.getElementById('smoke');
                smoke.style.left = '60%';
                smoke.style.top = '30%';
                smoke.style.opacity = '1';
                smoke.style.transition = 'opacity 2s';
                setTimeout(() => { smoke.style.opacity = '0'; }, 2000);
            }
            
            // Update attempt message with dark humor
            const messages = [
                "The executioner cocks his pistol...",
                "That wasn't in the script!",
                "The victim is sweating bullets!",
                "The pistol isn't just for show!",
                "The executioner chuckles darkly...",
                "Was that a misfire?",
                "Final warning! The trigger finger is itchy..."
            ];
            const messageIndex = totalAttempts - remainingAttempts - 1;
            document.getElementById('attemptMessage').textContent = 
                " - " + messages[messageIndex % messages.length];
            
            // Remove animation classes after they play
            setTimeout(() => {
                document.getElementById('victim').classList.remove('shake');
                document.getElementById('pistol').classList.remove('shot');
            }, 1000);
        <% } %>
    </script>
</body>
</html>