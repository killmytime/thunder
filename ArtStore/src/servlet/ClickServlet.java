package servlet;

import service.ArtworkService;
import service.impl.ArtworkServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ClickServlet",value = "/click")
public class ClickServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private int clickNumber;
    private ArtworkService artworkService=new ArtworkServiceImpl();

    public void init()
    {
        // 重置点击计数器
        clickNumber = 0;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        init();
        String artworkId = request.getSession().getAttribute("artworkID").toString();
        // 增加 clickNumber
        int artworkID=Integer.parseInt(artworkId);
        clickNumber=artworkService.getOne(artworkID).getClickNumber();
        clickNumber++;
        artworkService.update(artworkID,clickNumber);
    }

}
