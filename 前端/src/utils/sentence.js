export function getSentence() {
    // fetch('https://v1.hitokoto.cn') // 使用Fetch API向指定的URL发起HTTP请求
    // .then(response => response.json()) // .json()方法用于解析JSON格式的响应体。
    // .then(data => { // 解析后的JSON数据（在这里被命名为data）作为参数
    // //   const hitokoto = document.querySelector('#hitokoto_text')
    // //   hitokoto.innerText = data.hitokoto
    //     console.log(data.hitokoto)
    //     return data.hitokoto;
    // })
    // .catch(console.error)

    const xhr = new XMLHttpRequest();
    xhr.open('GET', 'https://v1.hitokoto.cn', false); // 设置为同步请求
    xhr.send();

    if (xhr.status === 200) {
        const data = JSON.parse(xhr.responseText);
        // const hitokoto = document.querySelector('#hitokoto_text');
        // hitokoto.innerText = data.hitokoto;
        return data.hitokoto;
    } else {
        console.error('Failed to fetch data:', xhr.statusText);
    }
}