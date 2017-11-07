
$(document).ready(function () {
    let show = function (data) {

        //首先去除所有已有的结果
        if ($('#searchResult').children()) {
            $("#searchResult").children().remove();
        }
        let art = JSON.parse(data);
        if (art.length==0){
            $("#searchResult").appendChild(document.createTextNode("No result."))
        }else {
            sortSwitch(art);
        art.forEach(function (artwork) {
            let artworkID = artwork.artWorkId;
            let div1 = document.createElement("div");
            div1.setAttribute("class","row beautifulBoard");
            let div2 = document.createElement("div");
            div2.setAttribute("class","col-xs-6 col-md-4");
            let a = document.createElement("a");
            a.setAttribute("href","details.jsp?ArtworkID="+artworkID);
            let img = document.createElement("img");
            img.setAttribute("src","./art-images/works/large/"+artwork.imageFileName+".jpg");
            img.setAttribute("alt",artwork.title);
            img.setAttribute("style","width: 20vw;height: 40vh");
            a.appendChild(img);
            div2.appendChild(a);
            div1.appendChild(div2);
            let div3 = document.createElement("div");
            div3.setAttribute("class","col-xs-12 col-md-8");
            let title = document.createElement("h3");
            title.appendChild(document.createTextNode("Title:"+artwork.title));
            let price = document.createElement("p");
            price.appendChild(document.createTextNode("Cost:"+artwork.cost));
            let year = document.createElement("p");
            year.appendChild(document.createTextNode("Year:"+artwork.yearOfWork));
            let description = document.createElement("p");
            description.appendChild(document.createTextNode("Description"+artwork.description));
            div3.appendChild(title);
            div3.appendChild(price);
            div3.appendChild(year);
            div3.appendChild(description);
            div1.appendChild(div3);
            $("#searchResult").append(div1);
        })
        }
    };
    //利用sort给json数组排序，不得不说这样写真是反人类，（假设数组是这样的来做，结果数组必须是这样才可以。。。还是java好）
    let sortedByDate=function date(artwork1,artwork2) {
        return artwork1.yearOfWork-artwork2.yearOfWork;
    };
    let sortedByPrice=function price(artwork1,artwork2) {
        return artwork1.cost-artwork2.cost;
    };
    let sortSwitch=function (art) {
        let sortBy=$('input:radio[name="sort"]:checked').val();
        switch (sortBy){
            case "price":
                art.sort(sortedByPrice);
                break;
            case "date":
                art.sort(sortedByDate);
        }
    }
    let Switch=function() {
        let type=$('input:radio[name="filter"]:checked').val();

            switch (type) {
                case "title":
                    //一开始甚至以为searchInput是个坑，总是empty，后来发现是和引用的nav里的search冲突了，唉。。。
                    if ($("#searchInput").val() === "") {
                        alert("Empty!")
                    }else {
                        let keyWord = $("#searchInput").val();
                        $.get("./search",{"type":"title","keyWord":keyWord},show)
                    }
                    break;

                case "description" :
                    if ($("#searchInput").val() === "") {
                        alert("Empty!")
                    }else {
                        var keyWord = $("#searchInput").val();
                        $.get("./search",{"type":"description","keyWord":keyWord},show)
                    }
                    break;

                case "artist" :
                    if ($("#searchInput").val() === "")  {
                        alert("Empty!")
                    }else {
                        var keyWord = $("#searchInput").val();
                        $.get("./search",{"type":"artist","keyWord":keyWord},show)
                    };
                    break;
            }
    }
    $("#filter").click(Switch);
    $("#searchInput").keydown(function () {
        if (event.keyCode=="13"){
            Switch;
        }
    })
});