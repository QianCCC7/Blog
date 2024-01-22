
//初始化时间
const initDate = (oldDate, full) => {
    var odate = new Date(oldDate);
    var year = odate.getFullYear();
    var month = odate.getMonth() < 9 ? '0' + (odate.getMonth() + 1) : odate.getMonth() + 1;
    var date = odate.getDate() < 10 ? '0' + odate.getDate() : odate.getDate();
    if (full == 'all') {
        if (oldDate != null) {
            var t = oldDate.split(" ")[0].split("-");
            return t[0] + '年' + t[1] + '月' + t[2] + '日';
        }
        return null;
    } else if (full == 'year') {
        return year
    } else if (full == 'month') {
        return odate.getMonth() + 1
    } else if (full == 'date') {
        return date
    } else if (full == 'newDate') {
        return year + '年' + month + '月' + date + '日';
    }
}

export {
    initDate//设置时间
}
