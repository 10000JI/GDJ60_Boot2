/**
 *  Join Form에서 검증
 */
$('#userName').blur(idDuplicateCheck);

$('#passWordCheck').blur(pwDuplicateCheck);

function idDuplicateCheck(){
	
	$.ajax({
		type:"GET",
		url:"./idDuplicateCheck",
		data:{
			userName:$('#userName').val()
		},
		success:function(result){
			if(result){
					console.log("사용가능합니다.")
				}else {
					console.log("중복입니다.")
			}
		},
		error:function(){
			console.log('error')
		}
	})
}

function pwDuplicateCheck(){
	$.ajax({
		type:"GET",
		url:"./pwDuplicateCheck",
		data:{
			passWord:$('#passWord').val(),
			passWordCheck:$('#passWordCheck').val()
		},
		success:function(result){
			if(result){
					console.log("일치합니다.")
				}else {
					console.log("불일치합니다.")
			}
		},
		error:function(){
			console.log('error')
		}
	}
		
	)
}



 
