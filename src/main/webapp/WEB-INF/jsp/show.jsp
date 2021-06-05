<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Qr Code Detail</title>
</head>
<style>
body{
  padding: 0;
  margin: 0;
  background-color: #000;
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100vh
}
h1{
  color: #484848;
  font-size: 50px;
  font-weight: bold;
  font-family: monospace;
  letter-spacing: 7px;
  cursor: pointer
}
h1 span{
  transition: .5s linear
}
h1:hover span:nth-child(1){
  margin-right: 5px
}
h1:hover span:nth-child(1):after{
  content: "'";
}
h1:hover span:nth-child(2){
  margin-left: 30px
}
h1:hover span{
  color: #fff;
  text-shadow: 0 0 10px #fff,
               0 0 20px #fff, 
               0 0 40px #fff;
}
/*made with ❤, by qpi65*/
</style>
<body>
<h1><span>Wel</span><span>Come</span></h1>
<h1><span>${qrcode.getFirstname() }</span>${qrcode.getLastname() }<span> &nbsp;&nbsp;&nbsp;${qrcode.getPhone() }</span></h1>
<%-- ${qrcode.getData() } --%>
</body>
<script>
         setTimeout(function(){
            window.location.href = 'http://localhost:8080/scan';
         }, 5000);
      </script>
</html>