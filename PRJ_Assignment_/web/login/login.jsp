<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Your JSP Page</title>
        <style>
            .container {
                max-height: 300px;
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
        <div style="display: flex; justify-content: space-evenly">
            <h1><span>FPT University Academic Portal</span> </h1> 
            <div style="display: flex; flex-direction: column">
                <p style="font-weight: bold"> FAP mobile app (myFAP) is ready at </p>
                <div>
                    <tr>
                        <td><a href="https://apps.apple.com/app/id1527723314">
                                <img src="https://fap.fpt.edu.vn/images/app-store.png" style="width: 120px; height: 40px" alt="apple store" /></a></td>
                        <td><a href="https://play.google.com/store/apps/details?id=com.fuct">
                                <img src="https://fap.fpt.edu.vn/images/play-store.png" style="width: 120px; height: 40px" alt="google store" /></a></td>
                    </tr> 
                </div>
            </div>
        </div>


        <div id="nav-user" style="float: right;width: 100%; padding: 10px 10px;background-color: lightgrey;opacity: 50%">   </div>
        <br>
        <br>
        <div style="text-align: center">
            <c:if test="${not empty sessionScope.loginResult}">
                <div style="color: red;">
                    ${sessionScope.loginResult}
                </div>
                <c:set var="sessionScope.loginResult" value="" />
            </c:if>

        </div>

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

        <p style="text-align: center; text-decoration: none">  
            © Powered by <a href="http://fpt.edu.vn" target="_blank" style="text-decoration: none">FPT University</a>&nbsp;|&nbsp;
            <a href="http://cms.fpt.edu.vn/ style="text-decoration: none"" target="_blank" style="text-decoration: none">CMS</a>&nbsp;|&nbsp; <a href="http://library.fpt.edu.vn" target="_blank" style="text-decoration: none">library</a>&nbsp;|&nbsp; <a href="http://library.books24x7.com" style="text-decoration: none" target="_blank">books24x7</a>
            <span id="ctl00_lblHelpdesk"></span>


            <script>
                function redirectToDisplay() {
                    window.location.href = "display";
                }
            </script>
    </body>
</html>
