function Rapid () {
    this.recommendNative = function () {
        if (navigator.userAgent.match(/Android/i) && typeof ___androidExists !== "undefined") {
            if(confirm("Do you want to run this website in Rapid?") ) {
                location.replace("rapid://" + document.URL);
            }
        }
    };
}

var rapid = new Rapid();
