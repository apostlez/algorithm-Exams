var fs = require('fs');
if (process.platform === 'linux') {
    const filePath = '/dev/stdin';
    var inputText = fs.readFileSync(filePath).toString();
    var t = parseInt(inputText);
    console.log(t);
    for (var ti = 0; ti < t; ti++) {
        inputText = fs.readFileSync(filePath).toString();
      var n = parseInt(inputText);
      console.log(n);
      var a = fs
        .readFileSync(filePath)
        .toString()
        .split(' ')
        .map((value) => parseInt(value));
      console.log(zigzag(0, n - 1, a));
    }
} else {
    const filePath = __dirname + '/input.txt';
    var inputText = fs.readFileSync(filePath).toString().split('\n');
    var t = parseInt(inputText[0].trim());
    for (var ti = 1; ti < t*2;) {
      var n = parseInt(inputText[ti++]);
      var a = inputText[ti++]
        .split(' ')
        .map((value) => parseInt(value));
      console.log(zigzag(0, n - 1, a));
    }
}

function zigzag(k, j, a) {
  var ret = 0;
  var length = 0;
  if (k >= j) return 0;
  if (a[k] === a[k + 1]) {
    return zigzag(k + 1, j, a);
  }
  if (a[k] < a[k + 1]) {
    length = isNextSmall(k + 1, j, a) + 1;
  } else {
    length = isNextBig(k + 1, j, a) + 1;
  }
  ret += (length * (length+1)) / 2;
  ret += zigzag(k + length, j, a);
  return ret;
}
function isNextSmall(k, j, a) {
  if (k < j && a[k] > a[k + 1]) {
    return isNextBig(k + 1, j, a) + 1;
  }
  return 0;
}
function isNextBig(k, j, a) {
  if (k < j && a[k] < a[k + 1]) {
    return isNextSmall(k + 1, j, a) + 1;
  }
  return 0;
}