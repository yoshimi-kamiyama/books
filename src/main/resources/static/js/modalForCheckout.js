/**
 * 
 */
window.onload = function () {
    var today = new Date();
    today.setDate(today.getDate());
    var yyyy = today.getFullYear();
    var mm = ("0" + (today.getMonth() + 2)).slice(-2);
    var dd = ("0" + today.getDate()).slice(-2);
    document.getElementById("duedate").value = yyyy + '-' + mm + '-' + dd;    
}

// ボタンがクリックされた時
//buttonOpen.addEventListener('click', modalOpen);
function modalOpen() {
	let request = new XMLHttpRequest();
	request.onreadystatechange = function(){
		if (request.readyState == 4){
		    // 受信したデータの処理を記述する
		    let data = JSON.parse(request.responseText);
			let node = document.getElementById("result");
			var tableHeader = '<input class=\"search-input user-search\" id=\"username\" type=\"text\">' +
			'<button id=\"usersubmit\" class=\"btn\" onclick=\"userSubmit()\">決定</button>' + 
			'<table class=\"table table-striped\">' +
		      '<thead>' +
		        '<tr>' +
		          '<th class=\"user-table-header\">選択</th>' +
		          '<th class=\"user-table-header\">利用者</th>' +
		        '</tr>' +
		      '</thead>' + 
		      '<tbody>';
		    var tableEnd = '</tbody></table>';
		    var tableData = '';
		    var tr = '<tr class=\"userrow\">';
		    var trEnd = '</tr>';
		    var td = '<td>';
		    var tdEnd = '</td>';
		    
		    var spanP = '<span class=\"tooltip\">';
		    var spanC = '<span class=\"description_bottom\">';
		    var spanEnd = '</span>';

		    for(let i = 0; i < data.length; i++){
				var age = getAge(data[i].birth);
				
				tableData = tableData + tr + 
							td + '<input type=\"radio\" value=\"' + 
							data[i].userId + '\" name=\"userid\">' + tdEnd +
							'<td name=\"' + data[i].lastName + data[i].firstName + '\">' + 
							spanP + data[i].lastName + ' ' + data[i].firstName + 
							spanC +
							'年齢： ' + age + '<br>' +
							'性別： ' + data[i].sex + '<br>' +
							'電話： ' + data[i].tel + '<br>' +
							'メール： ' + data[i].mail + '<br>' +
							'住所： ' + data[i].address + '<br>' +
							spanEnd + tdEnd + 
							trEnd;
			}
			node.innerHTML = tableHeader + tableData + tableEnd;
			
			const seachInput = document.getElementById('username');
			seachInput.addEventListener('keydown', function(){
				filter(seachInput.value);
			}, false);
		}
	}
	
	request.open('GET', 'http://localhost:8080/user/json', true);
//	console.log(request.readyState);
	request.send(null);
	
	const modal = document.getElementById('easyModal');
	modal.style.display = 'block';
}

function filter(inputText){
	var users = document.querySelectorAll('.userrow');
//	console.log(users);
	console.log(inputText);
	users.forEach(function(user){ 
//		console.log(user.lastChild.innerText)
		userName = user.lastChild.getAttribute('name');
//		console.log(userName);
		user.style.display ="block";
		if(userName.indexOf(inputText) != -1){
			user.style.display ="block";
		}else{
			user.style.display ="none";
		}
	});
}

// バツ印がクリックされた時
//buttonClose.addEventListener('click', modalClose);
function modalClose() {
	const modal = document.getElementById('easyModal');
	modal.style.display = 'none';
}

function userSubmit(){
//	console.log("userSubmit");
	var chkBoxes = document.querySelectorAll("input[name='userid']");
	var userId = '';
	chkBoxes.forEach(function(chkBox){
		if(chkBox.checked){
			userId = chkBox.getAttribute('value'); 
		}
	});
	if(userId){
		searchUser(userId);
		modalClose();
	}else{
		alert('ユーザが選択されていません');
	}
}

function searchUser(userId){
	let request = new XMLHttpRequest();
	request.onreadystatechange = function(){
		if (request.readyState == 4){
			let data = JSON.parse(request.responseText);
			var label = document.querySelector('#selecteduser');
			var div = document.querySelector('#userinfo');
			label.textContent = data.lastName + ' ' + data.firstName; 
//			console.log(data);
			var age = getAge(data.birth);
			div.innerHTML = '<input type=\"hidden\" name=\"userId\" value=\"' + data.userId + '\">' +
			'<table class=\"modal-table table-striped\">' +
			'<tr><td>年齢</td><td>' + age + '</td></tr>' +
			'<tr><td>性別</td><td>' + data.sex + '</td></tr>' +
			'<tr><td>電話</td><td>' + data.tel + '</td></tr>' +
			'<tr><td>メール</td><td>' + data.mail + '</td></tr>' +
			'<tr><td>住所</td><td>' + data.address + '</td></tr>' +
			'</table>';
		}
	}
	
	request.open('GET', 'http://localhost:8080/user/info?id=' + userId, true);
	request.send(null);
}

function getAge(birth){
	var today = new Date();
	var year = birth.slice(0, 4);
	var month = birth.slice(5, 7);
	var day = birth.slice(8, 10);
	var thisYearsBirthday = new Date(today.getFullYear(), Number(month) - 1, Number(day));
	var age =  today.getFullYear() - Number(year);
	if (today < thisYearsBirthday) age--;
	return age;
}

function checkout(){
	
	var userLabelNode = document.querySelector('#selecteduser');
	var dueDateNode = document.querySelector('#duedate');	
	var nowDate = new Date();
	var dueDate = new Date(dueDateNode.value);
//	console.log(userLabelNode.textContent);
//	console.log(Number.isNaN(dueDate.getTime()));
	
	if(userLabelNode.textContent != '未選択'){
		if(nowDate > dueDate){
			alert('無効な日付です\n明日以降の日付を入力して下さい');
		}else{
			if(Number.isNaN(dueDate.getTime())){
				alert('無効な日付です');
			}else{
				var result = confirm('貸出処理を実行します');
				if (result) {
					document.checkoutform.submit();
				}	
			}  
		}
	}else{
		alert('ユーザが選択されていません');
	}
}

function cancelClick(){
	history.back();
}

/*
{
"userId":1,
"lastName":"伊藤",
"firstName":"康弘",
"birth":"2005-05-29T15:00:00.000+00:00",
"sex":"F",
"tel":"070-9390-2844",
"mail":"tsubasatanaka@example.org",
"address":"54124 David Camp Apt. 280 East Peggyland, VI 09170",
"userRegistered":"2022-09-07T15:00:00.000+00:00"
},
*/