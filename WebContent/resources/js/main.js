
function ajax(url) {
	return new Promise(function(resolve, reject) {
		let xhr = new XMLHttpRequest();		

		xhr.onload = function() {
			if (xhr.status == 200) {
				resolve(xhr.response);
			} else {
				reject(Error(xhr.statusText));
			}
		}
		
		xhr.open('GET', '/reservation/api/' + url);
		xhr.send();

	})
}

function promotionTemplate(promotionItem, saveFileName) {
	promotionItem = promotionItem.replace("{saveFileName}", saveFileName);
	return promotionItem;
}

function categoryTemplate(categoryItem, id, name) {
	categoryItem = categoryItem.replace("{id}", id);
	categoryItem = categoryItem.replace("{name}", name);
	return categoryItem;
}

function productTemplate(productItem, id, description, placeName, content, saveFileName) {
	productItem = productItem.replace("{id}", id);
	productItem = productItem.replace("{ description }", description);
	productItem = productItem.replace("{placeName}", placeName);
	productItem = productItem.replace("{content}", content);
	productItem = productItem.replace("{saveFileName}", saveFileName);
	return productItem;
}

function slide(ul, length) {
	let clone = ul.firstElementChild.cloneNode(true);
	ul.appendChild(clone);
	let curIndex = 0;

	setInterval(function() {
		ul.style.transition = "0.8s";
		ul.style.transform = "translate3d(-" + 414 * (curIndex + 1)
				+ "px, 0px, 0px)";

		curIndex++;

		if (curIndex === length) {
			setTimeout(function() {
				ul.style.transition = '0s';
				ul.style.transform = "translate3d(0px, 0px, 0px)";
			}, 801)
			curIndex = 0;
		}

	}, 1000);
}

function setActiveTab (tabName, category, count) {
	let tab = document.querySelectorAll("ul.event_tab_lst a");
	tab.forEach(function (item) {
		if(tabName == item.textContent){
			item.className = "anchor active";
		}else{
			item.className = "anchor";
		}
	})
	productByCategory(category, 0, count);
}

function productByCategory(id, start, count) {
	
	ajax('products?categoryId=' + id +'&start=' + start).then(
			function(response) {
				const product = JSON.parse(response);
				let evenProductUl = document
				.querySelector("#even_event_box");
				let oddProductUl = document
				.querySelector("#odd_event_box");
				let productItem = document
				.querySelector("#itemList").innerHTML;
				
				if(start == 0) {
					setProductList(product, evenProductUl, oddProductUl, productItem);
				}else{
					addProductList(product, evenProductUl, oddProductUl, productItem);
				}
				
				if(id == 0) {
					updateCountSpan(product.totalCount);
				}else if(count != undefined){
					updateCountSpan(count[id-1]);
				}
				
				if(product.items.length != 4) {
					document.querySelector("div.more button").style.display = "none";
				}else{
					document.querySelector("div.more button").style.display = "block";
				}
				
			})	
}

function setProductList(product, evenProductUl, oddProductUl, productItem) {
	let evenResult = "";
	let oddResult = "";
	
	product.items.forEach(function(item, index) {
		if(index%2 == 0){
			evenResult += productTemplate(productItem, item.productId, item.productDescription, item.placeName, item.productContent, item.productImageUrl).trim();	
		}else{
			oddResult += productTemplate(productItem, item.productId, item.productDescription, item.placeName, item.productContent, item.productImageUrl).trim();
		}
	})			
	
	evenProductUl.innerHTML = evenResult;
	oddProductUl.innerHTML = oddResult;

}
function addProductList(product, evenProductUl, oddProductUl, productItem) {
	product.items.forEach(function(item, index) {
		if(index%2 == 0){
			evenProductUl.innerHTML += productTemplate(productItem, item.productId, item.productDescription, item.placeName, item.productContent, item.productImageUrl).trim();	
		}else{
			oddProductUl.innerHTML += productTemplate(productItem, item.productId, item.productDescription, item.placeName, item.productContent, item.productImageUrl).trim();
		}
	})			
}

function updateCountSpan(count) {
	let countSpan = document
	.querySelector("p.event_lst_txt span");
	
	countSpan.innerHTML = count + "개";
}


function init() {
	
	let categoryNum = 0;
	let start = 0;
	let moreBtn = document.querySelector("div.more button");
		
	ajax('promotions').then(
			function(response) {
				const promotion = JSON.parse(response);
				let promotionUl = document
						.querySelector("ul.visual_img");
				let promotionItem = document
						.querySelector("#promotionItem").innerHTML;

				promotion.forEach(function(item) {
					promotionUl.innerHTML += promotionTemplate(promotionItem,
							item.saveFileName);
				})
				
				slide(promotionUl, promotion.length);
				
			}, function(error) {
				console.log(error);
			})
			
	ajax('categories').then(
			function(response) {
				const category = JSON.parse(response);
				let categoryUl = document
						.querySelector("ul.event_tab_lst");
				let categoryItem = document
						.querySelector("#categoryItem").innerHTML;
				let categoryCount = [];
				
				category.forEach(function(item) {
					categoryUl.innerHTML += categoryTemplate(categoryItem, item.id, item.name)
					categoryCount.push(item.count);
				})
				
				categoryUl.addEventListener("click", function(evt) {
					start = 0;
					categoryNum = evt.target.closest("li").getAttribute("data-category");
					let selectedTab = evt.target.closest("a").textContent;
					setActiveTab(selectedTab, categoryNum, categoryCount);
				})
				
			}, function(error) {
				console.log(error);
			})
			
	productByCategory(0, 0);

	moreBtn.addEventListener("click", function () {
		start += 4;
		productByCategory(categoryNum, start);
	})
			
}


