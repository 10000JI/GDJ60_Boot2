/**
 *  Board Form 유효성 검사
 */

/* const submitButton = document.getElementById("submitButton");
 const check = document.getElementsByClassName("check");
 
 submitButton.addEventListener("click",function(){
	 if(check.length>0){
		 submitButton.setAttribute("button")
	 }
 })*/
 $('#submitButton').click(function(){
	let checked = true;
	$('.check').each(function(index,chk){
		checked=false;
		if($(chk).val()==""){
			 if ($(chk).prop('type') == "text") {
                alert('입력란을 다시 확인해주세요');
                return false;
            }
            else if ($(chk).prop('type') == "file") {
                alert('파일업로드 오류');
                return false;
            }
		}
		if ($(".note-editable").children().children().prop("tagName") == "BR") {
	            alert('썸머노트 오류');
	            return false;
	    }
	})
	if(checked){
		$("#contactForm").submit();
	}
})