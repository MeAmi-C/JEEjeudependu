<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dark Comedy Hangman</title>
    <style>
        body {
            font-family: 'Courier New', monospace;
            background-color: #121212;
            color: #e0e0e0;
            text-align: center;
            margin: 0;
            padding: 0;
            height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            background-image: url('https://example.com/crime-scene-tape.jpg'); /* Add your own background image */
            background-size: cover;
            background-position: center;
        }
        
        .overlay {
            background-color: rgba(0, 0, 0, 0.7);
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(255, 0, 0, 0.5);
            max-width: 600px;
        }
        
        h1 {
            color: #ff4444;
            text-shadow: 0 0 10px rgba(255, 0, 0, 0.7);
            font-size: 3em;
            margin-bottom: 30px;
            letter-spacing: 5px;
        }
        
        p {
            font-size: 1.2em;
            margin-bottom: 30px;
            line-height: 1.6;
        }
        
        .start-button {
            padding: 15px 30px;
            font-size: 1.2em;
            background-color: #ff4444;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: all 0.3s;
            text-transform: uppercase;
            letter-spacing: 2px;
        }
        
        .start-button:hover {
            background-color: #ff0000;
            transform: scale(1.05);
            box-shadow: 0 0 15px rgba(255, 0, 0, 0.7);
        }
        
        .blink {
            animation: blink 2s infinite;
        }
        
        @keyframes blink {
            0%, 100% { opacity: 1; }
            50% { opacity: 0.5; }
        }
        
        .crime-scene {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            pointer-events: none;
            z-index: -1;
        }
        
        .tape {
            position: absolute;
            width: 100%;
            height: 40px;
            background-color: yellow;
            opacity: 0.7;
            transform: rotate(-5deg);
            top: 30%;
        }
        
        .tape:nth-child(2) {
            top: 60%;
            transform: rotate(5deg);
        }
    </style>
</head>
<body>
    <div class="crime-scene">
        <div class="tape"></div>
        <div class="tape"></div>
    </div>
    
    <div class="overlay">
        <h1>CRIME SCENE HANGMAN</h1>
        <p>Guess the word before the executioner gets bored and pulls the trigger!<br>
        (Don't worry, it's just a toy pistol... we think)</p>
        <form action="HangmanServlet" method="get">
            <input type="submit" class="start-button blink" value="ENTER THE CRIME SCENE">
        </form>
    </div>
</body>
</html>