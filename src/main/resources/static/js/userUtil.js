/**
 * 日付初期値
 */

window.onload = function () {
    document.querySelectorAll('th').forEach(th => th.onclick = sortRows);
    document.querySelector('th').classList.add('sort-desc');
    document.querySelectorAll('.chk-lable').forEach(cl => cl.onclick = filter);
	convertAge();
}

// 青年月日から年齢算出
function convertAge(){
	const table = document.querySelector("table");
	var today = new Date();
	
	for (let i = 1; i < table.rows.length; i++) {
		var str = table.rows[i].cells[1].textContent;
		var year = str.slice(0, 4);
		var month = str.slice(5, 7);
		var day = str.slice(8, 10);
		var thisYearsBirthday = new Date(today.getFullYear(), Number(month) - 1, Number(day));
		var age =  today.getFullYear() - Number(year);
		
		if (today < thisYearsBirthday) age--;
		table.rows[i].cells[1].textContent = age;
	}
}

function filter() {
	const elm =  document.querySelectorAll('input[name="sex"]');
	
	const table = document.querySelector("table");
	const columnIndex = 2;
	
	if (!elm[0].checked && elm[1].checked){
		console.log('可のみ表示');
		for (let i = 1; i < table.rows.length; i++) {
			table.rows[i].style.display = '';
			if (table.rows[i].cells[columnIndex].textContent == "M"){
				table.rows[i].style.display = 'none';
			}
		}
	}else if (elm[0].checked && !elm[1].checked){
		console.log('不可のみ表示');
		for (let i = 1; i < table.rows.length; i++) {
			table.rows[i].style.display = '';
			if (table.rows[i].cells[columnIndex].textContent == "F"){
				table.rows[i].style.display = 'none';
			}
		}
	}else if (elm[0].checked && elm[1].checked){
		console.log('すべて表示');	
		for (let i = 1; i < table.rows.length; i++) {
			table.rows[i].style.display = '';
		}

	}else{
		console.log('何もしない');
	}
	
}

/**
 * テーブルソート
 */
function sortRows() {
	var chkValue;
	var isNum = false;
	
    const table = document.querySelector("table");
    const records = [];
    for (let i = 1; i < table.rows.length; i++) {
      const record = {};
      record.row = table.rows[i];
      record.key = table.rows[i].cells[this.cellIndex].textContent;
      if (record.key) chkValue = record.key; 
      records.push(record);
    }
    if (!isNaN(chkValue) && chkValue) isNum = true;
    //console.log(isNum);
    if (this.classList.contains('sort-asc')) {
	  if(isNum) records.sort(compareKeysReverseNum);
	  else records.sort(compareKeysReverse);
      purgeSortMarker();
      this.classList.add('sort-desc');
    } else {
      if(isNum) records.sort(compareKeysNum);
      else records.sort(compareKeys);
      purgeSortMarker();
      this.classList.add('sort-asc');
    }
    for (let i = 0; i < records.length; i++) {
      table.appendChild(records[i].row);
    }
}

  function purgeSortMarker() {
    document.querySelectorAll('th').forEach(th => {
      th.classList.remove('sort-asc');
      th.classList.remove('sort-desc');
    });
  }

  function compareKeys(a, b) {
    if (a.key < b.key) return -1;
    if (a.key > b.key) return 1;
    return 0;
  }

  function compareKeysReverse(a, b) {
    if (a.key < b.key) return 1;
    if (a.key > b.key) return -1;
    return 0;
  }
 
  function compareKeysReverseNum(a, b) {
    return b.key - a.key;
  }
  
  function compareKeysNum(a, b) {
    return a.key - b.key;
  }
