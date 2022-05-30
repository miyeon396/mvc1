package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    //ctrl+o : 이 서블릿이 호출되면 이 서비스 메서드가 호출됨.
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service"); //soutm
        System.out.println("request = " + request); //soutv
        System.out.println("response = " + response); //soutv

        String username = request.getParameter("username");
        System.out.println("username = " + username);

        response.setContentType("text/plain"); // contentType에 들어감
        response.setCharacterEncoding("utf8");
        response.getWriter().write("hello "+username); // http body에 다 들어감
    }
}
