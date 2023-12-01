/**
 * ユーザ詳細ページ
 */

 
 // 削除機能
 async function deleteClick(userId){
	 
	 console.log(userId);
	 
	 var result = confirm('本当に削除しますか？');
	 
	if (result) {
		const url = "http://localhost:8080/user/delete";
		const form = new FormData();
		form.append('id', userId);
		const params = {
			method : "POST", 
			headers: {
					'X-CSRF-Token': getCsrfToken()
			},
			body : form
		};
		
		var res = await fetch(url, params);

		console.log(res);
		if (res.status == 200){
			alert("削除しました");	
		}else{
			alert("削除処理中に問題が発生しました");
		}
		console.log(res.url);
		window.location.assign(res.url);
	}
 }
 
 function updateData(){
	 
	var result = confirm('本当に更新しますか？');
	if (result) {
		document.updateform.submit();
	}	
 }
 
  function getCsrfToken(){
	 // jsからfetchでリクエストを送る場合はcsrfトークンを手動で持ってくる必要がある
	var csrfElm = document.querySelector('input[name="_csrf"]');
	var csrf_token = csrfElm.value;
	return csrf_token;
 }