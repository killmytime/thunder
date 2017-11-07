package servlet;

        import entity.Artwork;
        import service.SearchService;
        import service.impl.SearchServiceImpl;
        import net.sf.json.JSONArray;

        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import javax.servlet.http.HttpSession;
        import java.io.IOException;
        import java.io.PrintWriter;
        import java.util.ArrayList;
        import java.util.List;

@WebServlet(name = "SearchServlet",value = "/search")
public class SearchServlet extends HttpServlet {

    private SearchService searchService = new SearchServiceImpl();

    private void doRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");
        String keyWork = request.getParameter("keyWord");
        List<Artwork> artwork = new ArrayList<>();

        switch (type) {
            case "title" :
                artwork = (ArrayList<Artwork>)searchService.filterByTitle(keyWork);

                JSONArray jsonArray = JSONArray.fromObject(artwork);
                PrintWriter out = response.getWriter();
                out.print(jsonArray);
                break;

            case "description" :
                artwork = (ArrayList<Artwork>)searchService.filterByDescription(keyWork);

                JSONArray jsonArray1 = JSONArray.fromObject(artwork);
                out = response.getWriter();
                out.print(jsonArray1);
                break;

            case "artist" :
                artwork = (ArrayList<Artwork>)searchService.filterByArtist(keyWork);

                JSONArray jsonArray2 = JSONArray.fromObject(artwork);
                out = response.getWriter();
                out.print(jsonArray2);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doRequest(request,response);
    }
}

