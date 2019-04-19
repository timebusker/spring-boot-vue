/**
 * 时间格式解析
 * @param time
 * @returns {string}
 */
export function parseTime(time) {
  if (time) {
    var date = new Date(time)
    var year = date.getFullYear()
    /* 在日期格式中，月份是从0开始的，因此要加0
     * 使用三元表达式在小于10的前面加0，以达到格式统一  如 09:11:05
     * */
    var month = date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1
    var day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate()
    var hours = date.getHours() < 10 ? '0' + date.getHours() : date.getHours()
    var minutes = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()
    var seconds = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds()
    // 拼接
    let time = year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds
    console.log(time)
    return time
  } else {
    return ''
  }
}

/**
 * 计算时间差
 * @param time
 * @param option
 * @returns {string}
 */
export function diffTime(beginTime, endTime) {
  let dateBegin = new Date(beginTime);
  let dateEnd = new Date(endTime);
  //时间差的毫秒数
  let dateDiff = dateEnd.getTime() - dateBegin.getTime();
  //计算出相差天数
  let dayDiff = Math.floor(dateDiff / (24 * 3600 * 1000));
  //计算天数后剩余的毫秒数
  let leave1 = dateDiff % (24 * 3600 * 1000);
  //计算出小时数
  let hours = Math.floor(leave1 / (3600 * 1000));
  //计算相差分钟数
  let leave2 = leave1 % (3600 * 1000);
  //计算小时数后剩余的毫秒数
  //计算相差分钟数
  let minutes = Math.floor(leave2 / (60 * 1000));
  //计算相差秒数
  let leave3 = leave2 % (60 * 1000);     //计算分钟数后剩余的毫秒数
  let seconds = Math.round(leave3 / 1000);
  if (dayDiff) {
    return dayDiff + "天" + hours + "小时" + minutes + "分钟" + seconds + "秒"
  } else if (hours) {
    return hours + "小时" + minutes + "分钟" + seconds + "秒"
  } else if (minutes) {
    return minutes + "分钟" + seconds + "秒"
  } else if (seconds) {
    return seconds + "秒"
  }
}
