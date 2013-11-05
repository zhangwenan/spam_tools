var base64EncodeChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
var base64DecodeChars = new Array(
    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63,
    52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1,
    -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
    15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1,
    -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
    41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1);

function base64decode(str) {
    var c1, c2, c3, c4;
    var i, len, out;

    len = str.length;
    i = 0;
    out = "";
    while(i < len) {
        /* c1 */
        do {
            c1 = base64DecodeChars[str.charCodeAt(i++) & 0xff];
        } while(i < len && c1 == -1);
        if(c1 == -1)
            break;

        /* c2 */
        do {
            c2 = base64DecodeChars[str.charCodeAt(i++) & 0xff];
        } while(i < len && c2 == -1);
        if(c2 == -1)
            break;

        out += String.fromCharCode((c1 << 2) | ((c2 & 0x30) >> 4));

        /* c3 */
        do {
            c3 = str.charCodeAt(i++) & 0xff;
            if(c3 == 61)
                return out;
            c3 = base64DecodeChars[c3];
        } while(i < len && c3 == -1);
        if(c3 == -1)
            break;

        out += String.fromCharCode(((c2 & 0XF) << 4) | ((c3 & 0x3C) >> 2));

        /* c4 */
        do {
            c4 = str.charCodeAt(i++) & 0xff;
            if(c4 == 61)
                return out;
            c4 = base64DecodeChars[c4];
        } while(i < len && c4 == -1);
        if(c4 == -1)
            break;
        out += String.fromCharCode(((c3 & 0x03) << 6) | c4);
    }
    return out;
}
//
function buy(iid){
    var url = '/buy.php?iid='+iid;
    window.open(url);
    return false;
}
//
function echo(str){
    document.write(str);
}
//ads
var tj ='';
var link = '<a href="http://s.click.taobao.com/t_9?p=mm_18776918_0_0&l=http%3A%2F%2Fwww.tmall.com" target="_blank">天猫商城</a><a href="http://ju.taobao.com/?ali_trackid=2:mm_18776918_0_0:1368723496_3k7_2038240098" target="_blank">聚划算团购</a><a href="http://list.tmall.com/search_product.htm?spm=3.1000473.294621.1.9tR0v1&start_price=&end_price=&search_condition=24&cat=50025135&sort=s&style=g&vmarket=0&active=1&q=&ali_trackid=2:mm_18776918_0_0:1368723783_4k4_1772425702" target="_blank">品牌女装</a><a href="http://list.tmall.com/search_product.htm?spm=3.1000473.294621.47.hmYLCj&start_price=&end_price=&zk_type=0&pic_detail=1&search_condition=23&cat=50025174&sort=s&style=g&vmarket=0&from=sn_1_rightnav&active=1&q=&ali_trackid=2:mm_18776918_0_0:1368723860_4k3_154521411" target="_blank">流行男装</a><a href="http://mei.tmall.com/?ali_trackid=2:mm_18776918_0_0:1368723898_4k2_588620566" target="_blank">美容频道</a><a href="http://s.click.taobao.com/t_9?p=mm_18776918_0_0&l=http%3A%2F%2Flist.tmall.com%2Fsearch_product.htm%3Fcat%3D50023647%26style%3Dg" target="_blank">母婴频道</a><a href="http://jia.tmall.com/?ali_trackid=2:mm_18776918_0_0:1368724019_410_1248225219" target="_blank">家装馆</a><a href="http://list.tmall.com/search_product.htm?spm=3.1000473.295282.1.0ycjUN&3.357789.295282&pic_detail=1&cat=50025829&sort=s&style=g&vmarket=0&active=1&q=&ali_trackid=2:mm_18776918_0_0:1368724122_4k9_1138132383" target="_blank">畅销鞋包</a><a href="http://s.click.taobao.com/t_9?p=mm_18776918_0_0&l=http%3A%2F%2Fchaoshi.tmall.com" target="_blank">天猫超市</a><a href="http://trip.taobao.com/?ali_trackid=2:mm_18776918_0_0:1368723638_410_792140747" target="_blank">淘宝旅行</a><a href="http://s.click.taobao.com/t_9?p=mm_18776918_0_0&l=http%3A%2F%2F3c.tmall.com" target="_blank">电器商城</a>';

function linkForImg(){
    var images=document.getElementsByTagName("img");
    var imgLen=images.length;
    for(var i=0;i<imgLen;i++){
        images[i].onclick=function(){
            window.open(u);
        }
        images[i].title="去淘宝查看促销价";
        images[i].style.cursor="pointer";
        var this_src = images[i].src;
        var parter=/.*\/b\/timg([0-9]+)\/(.+)/g;
        images[i].src = this_src.replace(parter,"ht"+"tp"+":"+"/"+"/i"+"m"+"g"+"$1.ta"+"o"+"b"+"a"+"oc"+"dn."+"c"+"o"+"m/b"+"a"+"o"+"/$2");
    }
}
//
var $id = function(id) {
    return "string" == typeof id ? document.getElementById(id) : id;

};
function SeoLei_LazyLoad(b) {
    var c = $id(b).getElementsByTagName('img');
    for (var d = 0, j = c.length; d < j; d++) {
        lazyload(c[d])
    }
};
var addListener = function(b, c, d, e) {
        if (b.addEventListener) {
            b.addEventListener(c, d, e);
            return true
        } else if (b.attachEvent) {
            b['e' + c + d] = d;
            b[c + d] = function() {
                b['e' + c + d](window.event)
            };
            b.attachEvent('on' + c, b[c + d]);
            return true
        };
        return false
    },
    getObjPoint = function(b) {
        var c = y = 0;
        do {
            c += b.offsetLeft || 0;
            y += b.offsetTop || 0;
            b = b.offsetParent
        }
        while (b);
        return {
            'x': c,
            'y': y
        }
    },
    isIE = function() {
        if (/msie (\d+\.\d)/i.test(navigator.userAgent)) {
            return document.documentMode || parseFloat(RegExp.$1)
        };
        return 0
    };
function lazyload(b) {
    if (!b.src || b.complete) {
        return false
    };
    var this_src = b.src;
    var parter=/.*\/160\/timg([0-9]+)\/(.+)/g;
    this_src=this_src.replace(parter,"http://img$1.taobaocdn.com/bao/$2_160x160.jpg");
    b.setAttribute('gc_src', this_src);
    b.src = '/static/images/loading.gif';
    var action = function(c) {
        if (c.getAttribute('loaded')) {
            clearInterval(c.timer);
            return
        };
        var d = document.documentElement,
            e = document.body,
            f = (d && d.scrollTop || e && e.scrollTop || 0) - (d && d.clientTop || e && e.clientTop || 0),
            g = getObjPoint(c),
            h = g.y,
            i = d && d.clientHeight || e && e.clientHeight;
        if (Math.abs(h - f) < i) {
            c.setAttribute('loaded', 'loaded');
            c.src = c.getAttribute('gc_src');
            c.onload = c.onerror = c.onreadystatechange = function() {
                if (c && c.readyState && c.readyState != 'loaded' && c.readyState != 'complete') {
                    return false
                };
                c.onload = c.onreadystatechange = c.onerror = null;
                var j = 0; (function() {
                    j += 10;
                    c.style.filter = 'alpha(opacity=' + j + ')';
                    c.style.opacity = (j / 100);
                    if (j < 100) setTimeout(arguments.callee, 20)
                })()
            }
        }
    };
    if (isIE() && isIE() < 9) {
        b.timer = setInterval(function() {
                action(b)
            },
            200)
    } else {
        action(b);
        addListener(window, 'scroll',
            function() {
                action(b)
            },
            false)
    }
};