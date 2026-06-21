const searchBox =
    document.getElementById("searchBox");

const suggestionsDiv =
    document.getElementById("suggestions");

searchBox.addEventListener("input", async () => {

    const q = searchBox.value;

    if(q.length === 0){
        suggestionsDiv.innerHTML="";
        return;
    }

    suggestionsDiv.innerHTML =
        "<div class='suggestion'>Loading...</div>";

    try{

        const response =
            await fetch(`/suggest?q=${q}`);

        const data =
            await response.json();

        suggestionsDiv.innerHTML="";

        data.forEach(item=>{

            const div =
                document.createElement("div");

            div.className="suggestion";

            div.innerHTML =
                `${item.query}
                 <small>
                 (${item.totalCount})
                 </small>`;

            div.onclick=()=>{

                searchBox.value =
                    item.query;

                suggestionsDiv.innerHTML="";
            };

            suggestionsDiv.appendChild(div);
        });

    }catch(error){

        suggestionsDiv.innerHTML =
            "<div class='suggestion'>Error loading suggestions</div>";
    }
});

async function submitSearch(){

    const query =
        searchBox.value;

    await fetch(
        `/search?query=${query}`,
        {
            method:"POST"
        }
    );

    document.getElementById("result")
        .innerText =
        `You searched for "${query}"`;

    loadTrending();
}

async function loadTrending(){

    const response =
        await fetch("/trending");

    const data =
        await response.json();

    const list =
        document.getElementById("trending");

    list.innerHTML="";

    data.forEach(item=>{

        const li =
            document.createElement("li");

        li.innerText=item;

        list.appendChild(li);
    });
}

loadTrending();