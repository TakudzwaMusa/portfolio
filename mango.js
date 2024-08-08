var svg = document.getElementById("svg");
var block = document.getElementById("block");
var bm = document.getElementById("bm");
var counter=0;
function jump(){
    if(svg.classList == "animate"){return}
    svg.classList.add("animate");
    setTimeout(function(){
        svg.classList.remove("animate");
    },300);
}
var checkDead = setInterval(function() {
    let svgTop = parseInt(window.getComputedStyle(svg).getPropertyValue("top"));
    let blockLeft = parseInt(window.getComputedStyle(block).getPropertyValue("left"));
    let bmLeft = parseInt(window.getComputedStyle(bm).getPropertyValue("left"));
    if(blockLeft<20 && blockLeft>-20 && svgTop>= 130){
        block.style.animation = "none";
        alert("Game Over. score: "+Math.floor(counter/100));
        counter=0;
        block.style.animation = "block 1s infinite linear";
    }
     if (bmLeft < 20 && bmLeft > -20 && svgTop >= 110) {
        block.style.animation = "none";
        alert("Game Over. score: " + Math.floor(counter / 100));
        counter = 0;
        block.style.animation = "block 1s infinite linear";
    }
    else{
        counter++;
        document.getElementById("scoreSpan").innerHTML = Math.floor(counter/100);
    }
}, 10);