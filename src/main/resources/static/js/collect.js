function toggleCollect(button) {
    const postId = button.getAttribute("data-post-id");
    let isCollected = button.getAttribute("data-collected") === "true";
    const method = isCollected ? "DELETE" : "POST";

    // from HTML file get username
    const username = document.getElementById("accountBtn").getAttribute("UserName");


    fetch(`/collect/${username}/${postId}`, {
        method: method,
        headers: { 'Content-Type': 'application/json' },
        credentials: 'include'
    })
        .then(res => res.json())
        .then(data => {
            if (data.status === 'success') {
                isCollected = !isCollected;
                button.setAttribute("data-collected", isCollected);

                const icon = button.querySelector("i");
                const text = button.querySelector("span");

                if (isCollected) {
                    button.classList.remove("btn-outline-secondary");
                    button.classList.add("btn-danger");
                    icon.classList.remove("fa-heart-o");
                    icon.classList.add("fa-heart");
                    text.textContent = "取消收藏";
                } else {
                    button.classList.remove("btn-danger");
                    button.classList.add("btn-outline-secondary");
                    icon.classList.remove("fa-heart");
                    icon.classList.add("fa-heart-o");
                    text.textContent = "收藏";
                }
            } else {
                alert("操作失敗：" + data.message);
            }
        })
        .catch(err => {
            console.error("錯誤：", err);
            alert("⚠️ 系統錯誤");
        });
}
