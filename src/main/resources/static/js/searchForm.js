let userCollections = new Set();

function fetchCollections() {
    return fetch("/collect")
        .then(res => res.json())
        .then(data => {
            if (data.status === "success") {
                userCollections = new Set(data.collections);
            }
        });
}

function handleSearch(event) {
    event.preventDefault(); // 防止表單預設送出
    const keyword = document.getElementById("inputBox").value.trim();

    // 先抓 Collection 接著再處理 fetchPosts(keyword)
    fetchCollections().then(() =>{
       fetchPosts(keyword);
    });
}

function fetchPosts(keyword = "") {
    let url = "/searchposts";
    if (keyword) {
        url += "?keyword=" + encodeURIComponent(keyword);
    }

    fetch(url)
        .then(res => res.json())
        .then(posts => {

            console.log(" 拿到的資料:", posts)

            const container = document.getElementById("post-list");
            container.innerHTML = ""; // 清空原本內容

            posts.forEach(post => {
                // ⛑ 租金欄位
                const rentalRange = post["租金"] || {};
                const minRental = rentalRange.minRental;
                const maxRental = rentalRange.maxRental;
                let rentalText = "未知";
                if (minRental != null && maxRental != null) {
                    rentalText = minRental === maxRental
                        ? `${minRental}元`
                        : `${minRental} ~ ${maxRental}元`;
                }

                // ⛑ 格局
                const layout = post["格局"] || {};
                const layoutText = `${layout["廳"] || 0}廳 ${layout["衛"] || 0}衛 ${layout["房"] || 0}房`;

                // ⛑ 性別限制
                const gender = post["性別限制"] || {};
                let genderLimitText = "未知";
                if (gender.男 === 1 && gender.女 === 1) {
                    genderLimitText = "不限";
                } else if (gender.男 === 1) {
                    genderLimitText = "限男";
                } else if (gender.女 === 1) {
                    genderLimitText = "限女";
                }

                // ⛑ 聯絡資訊
                const contact = post["聯絡方式"]?.[0] || {};
                const contactPerson = contact["聯絡人"] || "未知";
                const phone = contact["手機"]?.[0] || "未知";
                const lineId = contact["lineID"]?.[0] || "未知";
                const lineLink = contact["lineLink"]?.[0] || "未知";
                const others = contact["others"]?.[0] || "未知";


                // ⛑ 其他欄位
                const address = post["地址"] || "未知";
                const area = post["坪數"]?.[0] ?? "未知";
                const subsidy = post["是否可租屋補助"] ?? "未知";
                const hasTop = post["是否有頂樓加蓋"] ?? "未知";
                const allowPet = post["是否可養寵物"] ?? "未知";
                const allowFish = post["是否可養魚"] ?? "未知";
                const allowCook = post["是否可開伙"] ?? "未知";
                const elevator = post["是否有電梯"] ?? "未知";
                const carPark = post["是否有汽車停車位"] ?? "未知";
                const scooterPark = post["是否有機車停車位"] ?? "未知";

                // oid 是否包括檢測
                const oid = post["_id"]?.["$oid"] || "";
                const isCollected = userCollections.has(oid);


                const html = `
                <div id="${oid}" class="card mb-4 shadow p-3 mb-5 bg-body rounded" style="width: 100%; max-width: 1700px; margin: auto;">
                    <div class="row g-0">
                        <div id="dataBlock">
                            <nav class="navbar navbar-light bg-light px-3 py-4">
                                <h3>租屋資料</h3>
                            </nav>
                            <div class="card-body">
                                <table class="table table-hover">
                                    <tbody>
                                        <tr><th>地址</th><td>${address}</td></tr>
                                        <tr><th>租金</th><td>${rentalText}</td></tr>
                                        <tr><th>租屋補助</th><td>${subsidy}</td></tr>
                                        <tr><th>坪數</th><td>${area}</td></tr>
                                        <tr><th>格局</th><td>${layoutText}</td></tr>
                                        <tr><th>是否有頂樓加蓋</th><td>${hasTop}</td></tr>
                                        <tr><th>性別限制</th><td>${genderLimitText}</td></tr>
                                        <tr><th>寵物</th><td>${allowPet}</td></tr>
                                        <tr><th>養魚</th><td>${allowFish}</td></tr>
                                        <tr><th>開伙</th><td>${allowCook}</td></tr>
                                        <tr><th>電梯</th><td>${elevator}</td></tr>
                                        <tr><th>機車停車位</th><td>${scooterPark}</td></tr>
                                        <tr><th>汽車停車位</th><td>${carPark}</td></tr>
                                        <tr>
                                            <th>聯絡方式</th>
                                            <td colspan="2">
                                                <div><span class="label">聯絡人：</span>${contactPerson}</div>
                                                <div><span class="label">電話：</span>${phone}</div>
                                                <div><span class="label">Line ID：</span>${lineId}</div>
                                                <div><span class="label">line Link : </span>${lineLink}</div>
                                                <div><span class="label">others : </span>${others}</div>
                                                
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="d-flex justify-content-end mb-3" style="padding-right: 20px;">
                                <button class="btn ${isCollected ? 'btn-danger' : 'btn-outline-secondary'} collect-btn"
                                        data-post-id="${oid}"
                                        data-collected="${isCollected}"
                                        onclick="toggleCollect(this)">
                                        <i class="fa ${isCollected ? 'fa-heart' : 'fa-heart-o'}"></i>
                                        <span>${isCollected ? '取消收藏' : '收藏'}</span>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>`;

                container.insertAdjacentHTML('beforeend', html);
            });

        })
        .catch(err => console.error("資料載入失敗:", err));
}
