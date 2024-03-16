<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Your JSP Page</title>
        
    </head>
    <body>
        
        <div class="container">
            
            
            <p> ${loginresult}</p>
                <form action="login" method="POST" class="login-form">
                    <div class="input-container">
                        User: <input type="text" name="username"/>
                    </div>
                    <div class="input-container">
                        Pass: <input type="password" name="password"/>
                    </div>
                    <div class="button-container">
                        <input type="submit" value="Login">
                    </div>
                </form>
        </div>

    </body>
</html>
