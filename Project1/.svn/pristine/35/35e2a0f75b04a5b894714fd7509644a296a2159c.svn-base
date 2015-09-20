
//There's not really any code here...
//this is the code which is constantly running in the background while the extension is running
//it only detects when the user clicks the icon, which is when our code actually starts

chrome.browserAction.onClicked.addListener(function(tab) {
  chrome.tabs.executeScript({
	  //run our main code file
    file: 'content_script.js'
  });
});
