<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chappal to Sneakers - Admin Dashboard</title>
    <style>
        body {
            background: #f5f5f5;
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 20px;
        }
        .dashboard {
            max-width: 1200px;
            margin: 0 auto;
            background: white;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 0 20px rgba(0,0,0,0.1);
        }
        .header {
            background: #ff6b6b;
            color: white;
            padding: 20px;
            border-radius: 10px;
            margin-bottom: 20px;
        }
        .welcome {
            font-size: 24px;
        }
        .logout {
            float: right;
            color: white;
            text-decoration: none;
            padding: 5px 15px;
            background: rgba(255,255,255,0.2);
            border-radius: 20px;
        }
        .content {
            padding: 20px;
        }
    </style>
</head>
<body>
    <div class="dashboard">
        <div class="header">
            <span class="welcome">Welcome, Admin!</span>
            <a href="logout" class="logout">Logout</a>
        </div>
        <div class="content">
            <h3>Admin Dashboard</h3>
            <p>Manage the sneaker store from here.</p>
        </div>
    </div>
</body>
</html>