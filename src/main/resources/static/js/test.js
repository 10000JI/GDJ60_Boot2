/**
 * 
 */

let test = document.getElementById('test');
let c1 = document.getElementById('c1');
test.addEventListener("click",function(){
	console.log('test');
	c1.innerHTML="테스트 성공"
})
