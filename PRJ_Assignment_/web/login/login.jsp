<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Your JSP Page</title>
        <style>
            body {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }

            .container {
                display: flex;
            }

            .left-box, .right-box {
                width: 605px; /* Widened by 50px */
                height: 150px;
                border: 1px solid #000;
                margin-right: 20px;
                position: relative;
            }

            .top-left-corner, .top-right-corner {
                position: absolute;
                width: 100px;
                height: 25px;
                background-color: #eb9316;
                border-radius: 3.9375px;
                box-shadow: #ffffff 0px 1px 0px 0px;
                color: #000;
                display: flex;
                justify-content: center;
                align-items: center;
            }

            .top-left-corner {
                top: 10px; /* Adjusted position */
                left: 10px;
                color: white;
            }

            .top-right-corner {
                top: 10px;
                right: 10px;
            }

            .login-form {
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                padding: 20px;
                box-sizing: border-box;
            }

            .button-container, .input-container {
                display: flex;
                justify-content: center;
                align-items: center;
                margin-top: 10px;
            }

            .input-container input {
                margin-right: 5px;

            }
            .button-container {

            }
            .left-box .button-container {
                display: flex;
                justify-content: center;
                align-items: center; /* Align to the bottom */
                height: 100%; /* Occupy full height of the left-box */
                margin-bottom: 10px; /* Added margin at the bottom */
            }

        </style>
    </head>
    <body>
        <div class="container">
            <div class="left-box">
                <div class="top-left-corner">Display</div>
                <div class="button-container">
                    <button onclick="redirectToDisplay()" style="background-color: #337ab7;
                            background-image: linear-gradient(rgb(51, 122, 183), rgb(51, 122, 183)); 
                            color: #fff; border: none; border-radius: 4px; box-shadow: #ffffff 0px 1px 0px 0px; padding: 4px 10px; cursor: pointer;">
                        Display
                    </button>
                </div>
            </div>
            <div class="right-box">
                <div class="top-left-corner">Login</div>
                <form action="login_auth" method="POST" class="login-form">
                    <div class="input-container">
                        Username: <input type="text" name="username"/>
                    </div>
                    <div class="input-container">
                        Password: <input type="password" name="password"/>
                    </div>
                    <div class="button-container">
                        <input type="submit" value="Login" style="background-color: #337ab7;
                               background-image: linear-gradient(rgb(51, 122, 183), rgb(51, 122, 183)); 
                               color: #fff; border: none; border-radius: 4px; box-shadow: #ffffff 0px 1px 0px 0px;padding: 4px 10px;  cursor: pointer;">
                    </div>
                </form>
            </div>
        </div>

        <script>
            function redirectToDisplay() {
                window.location.href = "display";
            }
        </script>
    </body>
</html>
