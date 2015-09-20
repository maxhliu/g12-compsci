/*
Mister Lim
ICS4U-3
28 October 2013
Liu_Max_Project1
*/

//works best on blag.xkcd.com and what-if.xkcd.com

//see if there's an article tag available
//some HTML5 websites have this to indicate the main content
var article = document.getElementsByTagName("article")[0];
//if there isn't an article, don't do this
if (article != null)
	//remove everything but the article
	document.body.innerHTML = article.innerHTML;

//remove pesky HTML fonts and images with regular expressions
//strings matching "<font" then any number of characters that isn't a ">" ending in ">"
//basically strings of the form <font|FILLERTEXT|>
//for some reason CSS doesn't override these tags so they must be ERADICATED
document.body.innerHTML = document.body.innerHTML.replace(/<font[^>]*>/g, "");
//the same thing for images
document.body.innerHTML = document.body.innerHTML.replace(/<img[^>]*>/g, "");

//now get all the text for project2 involving sorting
var texts = document.body.getElementsByTagName("p");
//go through each element in texts
for (var i = 0; i < texts.length; i++) {
	//print it to the console so we can copy it
	console.log(texts[i].innerText);
}


//set styles in CSS for the body

//allow the viewer to scroll through the webpage
document.body.style.overflow = 'auto';
//sets the background color to a color slightly greyer than white
document.body.style.backgroundColor = 'rgb(250, 250, 250)';
//sets the font to the best font ever, Segoe UI (available on all windows systems)
//use sans-serif in case it isn't windows
document.body.style.fontFamily = '"Segoe UI", Segoe UI, sans-serif';
//set the text to twice its normal size
document.body.style.fontSize = '2em';
//set some puffy margins
document.body.style.margin = '50px 160px';
//center the text
document.body.style.textAlign = 'center';
//add some spacing between the lines
document.body.style.lineHeight = '130%';
//set the text color to black
document.body.style.color = 'rgb(0,0,0)';
