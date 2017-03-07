<div id="playercontainer"></div>
<script type="text/javascript">
var script = document.createElement("script");
script.setAttribute("type","text/javascript");
script.setAttribute("src","http://resource.bcevod.com/player/cyberplayer.js");
script.onload =script.onreadystatechane = function() {
  var player = cyberplayer("playercontainer").setup({
        width: 320,
        height: 240,
        file: "${videoFile}",
        image: "${image}",
        autostart: false,
        stretching: "uniform",
        repeat: false,
        volume: 100,
        controls: true,
        ak: '2763e1ed755945aab0e3cea803421f4b'
    });
}
document.body.appendChild(script);
</script>