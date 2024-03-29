package com.hz.world.common.constant;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProvinceConstants {

	/** 所在地-省份 **/
	public static Map<String, String> PROVINCE_MAP = new ConcurrentHashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("110000", "北京市");
			put("120000", "天津市");
			put("130000", "河北省");
			put("140000", "山西省");
			put("150000", "内蒙古");
			put("210000", "辽宁省");
			put("220000", "吉林省");
			put("230000", "黑龙江");
			put("310000", "上海市");
			put("320000", "江苏省");
			put("330000", "浙江省");
			put("340000", "安徽省");
			put("350000", "福建省");
			put("360000", "江西省");
			put("370000", "山东省");
			put("410000", "河南省");
			put("420000", "湖北省");
			put("430000", "湖南省");
			put("440000", "广东省");
			put("450000", "广西");
			put("460000", "海南省");
			put("500000", "重庆市");
			put("510000", "四川省");
			put("520000", "贵州省");
			put("530000", "云南省");
			put("540000", "西藏");
			put("610000", "陕西省");
			put("620000", "甘肃省");
			put("630000", "青海省");
			put("640000", "宁夏");
			put("650000", "新疆");
			put("710000", "台湾省");
			put("810000", "香港特别行政区");
			put("820000", "澳门特别行政区");
		}
	};
	
	/** 所在地-城市 **/
	public static Map<String, String> CITY_MAP = new ConcurrentHashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("110100", "东城区");
			put("110200", "西城区");
			put("110300", "崇文区");
			put("110400", "宣武区");
			put("110500", "朝阳区");
			put("110600", "丰台区");
			put("110700", "石景山区");
			put("110800", "海淀区");
			put("110900", "门头沟区");
			put("111000", "房山区");
			put("111100", "通州区");
			put("111200", "顺义区");
			put("111300", "昌平区");
			put("111400", "大兴区");
			put("111500", "怀柔区");
			put("111600", "平谷区");
			put("111700", "密云县");
			put("111800", "延庆县");
			put("120100", "和平区");
			put("120200", "河东区");
			put("120300", "河西区");
			put("120400", "南开区");
			put("120500", "河北区");
			put("120600", "红桥区");
			put("120700", "塘沽区");
			put("120800", "汉沽区");
			put("120900", "大港区");
			put("121000", "东丽区");
			put("121100", "西青区");
			put("121200", "津南区");
			put("121300", "北辰区");
			put("121400", "武清区");
			put("121500", "宝坻区");
			put("121600", "宁河县");
			put("121700", "静海县");
			put("121800", "蓟县");
			put("130100", "石家庄");
			put("130200", "唐山市");
			put("130300", "秦皇岛");
			put("130400", "邯郸市");
			put("130500", "邢台市");
			put("130600", "保定市");
			put("130700", "张家口");
			put("130800", "承德市");
			put("130900", "沧州市");
			put("131000", "廊坊市");
			put("131100", "衡水市");
			put("140100", "太原市");
			put("140200", "大同市");
			put("140300", "阳泉市");
			put("140400", "长治市");
			put("140500", "晋城市");
			put("140600", "朔州市");
			put("140700", "晋中市");
			put("140800", "运城市");
			put("140900", "忻州市");
			put("141000", "临汾市");
			put("141100", "吕梁市");
			put("150100", "呼和浩特");
			put("150200", "包头市");
			put("150300", "乌海市");
			put("150400", "赤峰市");
			put("150500", "通辽市");
			put("150600", "鄂尔多斯市");
			put("150700", "呼伦贝尔市");
			put("150800", "巴彦淖尔市");
			put("150900", "乌兰察布市");
			put("152200", "兴安盟");
			put("152500", "锡林郭勒盟");
			put("152900", "阿拉善盟");
			put("210100", "沈阳市");
			put("210200", "大连市");
			put("210300", "鞍山市");
			put("210400", "抚顺市");
			put("210500", "本溪市");
			put("210600", "丹东市");
			put("210700", "锦州市");
			put("210800", "营口市");
			put("210900", "阜新市");
			put("211000", "辽阳市");
			put("211100", "盘锦市");
			put("211200", "铁岭市");
			put("211300", "朝阳市");
			put("211400", "葫芦岛市");
			put("220100", "长春市");
			put("220200", "吉林市");
			put("220300", "四平市");
			put("220400", "辽源市");
			put("220500", "通化市");
			put("220600", "白山市");
			put("220700", "松原市");
			put("220800", "白城市");
			put("222400", "延边");
			put("230100", "哈尔滨市");
			put("230200", "齐齐哈尔市");
			put("230300", "鸡西市");
			put("230400", "鹤岗市");
			put("230500", "双鸭山市");
			put("230600", "大庆市");
			put("230700", "伊春市");
			put("230800", "佳木斯市");
			put("230900", "七台河市");
			put("231000", "牡丹江市");
			put("231100", "黑河市");
			put("231200", "绥化市");
			put("232700", "大兴安岭");
			put("310100", "黄浦区");
			put("310200", "卢湾区");
			put("310300", "徐汇区");
			put("310400", "长宁区");
			put("310500", "静安区");
			put("310600", "普陀区");
			put("310700", "闸北区");
			put("310800", "虹口区");
			put("310900", "杨浦区");
			put("311000", "闵行区");
			put("311100", "宝山区");
			put("311200", "嘉定区");
			put("311300", "浦东新区");
			put("311400", "金山区");
			put("311500", "松江区");
			put("311600", "青浦区");
			put("311700", "南汇区");
			put("311800", "奉贤区");
			put("311900", "崇明县");
			put("320100", "南京市");
			put("320200", "无锡市");
			put("320300", "徐州市");
			put("320400", "常州市");
			put("320500", "苏州市");
			put("320600", "南通市");
			put("320700", "连云港市");
			put("320800", "淮安市");
			put("320900", "盐城市");
			put("321000", "扬州市");
			put("321100", "镇江市");
			put("321200", "泰州市");
			put("321300", "宿迁市");
			put("330100", "杭州市");
			put("330200", "宁波市");
			put("330300", "温州市");
			put("330400", "嘉兴市");
			put("330500", "湖州市");
			put("330600", "绍兴市");
			put("330700", "金华市");
			put("330800", "衢州市");
			put("330900", "舟山市");
			put("331000", "台州市");
			put("331100", "丽水市");
			put("340100", "合肥市");
			put("340200", "芜湖市");
			put("340300", "蚌埠市");
			put("340400", "淮南市");
			put("340500", "马鞍山");
			put("340600", "淮北市");
			put("340700", "铜陵市");
			put("340800", "安庆市");
			put("341000", "黄山市");
			put("341100", "滁州市");
			put("341200", "阜阳市");
			put("341300", "宿州市");
			put("341400", "巢湖市");
			put("341500", "六安市");
			put("341600", "亳州市");
			put("341700", "池州市");
			put("341800", "宣城市");
			put("350100", "福州市");
			put("350200", "厦门市");
			put("350300", "莆田市");
			put("350400", "三明市");
			put("350500", "泉州市");
			put("350600", "漳州市");
			put("350700", "南平市");
			put("350800", "龙岩市");
			put("350900", "宁德市");
			put("360100", "南昌市");
			put("360200", "景德镇");
			put("360300", "萍乡市");
			put("360400", "九江市");
			put("360500", "新余市");
			put("360600", "鹰潭市");
			put("360700", "赣州市");
			put("360800", "吉安市");
			put("360900", "宜春市");
			put("361000", "抚州市");
			put("361100", "上饶市");
			put("370100", "济南市");
			put("370200", "青岛市");
			put("370300", "淄博市");
			put("370400", "枣庄市");
			put("370500", "东营市");
			put("370600", "烟台市");
			put("370700", "潍坊市");
			put("370800", "济宁市");
			put("370900", "泰安市");
			put("371000", "威海市");
			put("371100", "日照市");
			put("371200", "莱芜市");
			put("371300", "临沂市");
			put("371400", "德州市");
			put("371500", "聊城市");
			put("371600", "滨州市");
			put("371700", "荷泽市");
			put("410100", "郑州市");
			put("410200", "开封市");
			put("410300", "洛阳市");
			put("410400", "平顶山市");
			put("410500", "安阳市");
			put("410600", "鹤壁市");
			put("410700", "新乡市");
			put("410800", "焦作市");
			put("410900", "濮阳市");
			put("411000", "许昌市");
			put("411100", "漯河市");
			put("411200", "三门峡市");
			put("411300", "南阳市");
			put("411400", "商丘市");
			put("411500", "信阳市");
			put("411600", "周口市");
			put("411700", "驻马店市");
			put("420100", "武汉市");
			put("420200", "黄石市");
			put("420300", "十堰市");
			put("420500", "宜昌市");
			put("420600", "襄樊市");
			put("420700", "鄂州市");
			put("420800", "荆门市");
			put("420900", "孝感市");
			put("421000", "荆州市");
			put("421100", "黄冈市");
			put("421200", "咸宁市");
			put("421300", "随州市");
			put("422800", "恩施土家族");
			put("430100", "长沙市");
			put("430200", "株洲市");
			put("430300", "湘潭市");
			put("430400", "衡阳市");
			put("430500", "邵阳市");
			put("430600", "岳阳市");
			put("430700", "常德市");
			put("430800", "张家界市");
			put("430900", "益阳市");
			put("431000", "郴州市");
			put("431100", "永州市");
			put("431200", "怀化市");
			put("431300", "娄底市");
			put("433100", "湘西土家族");
			put("440100", "广州市");
			put("440200", "韶关市");
			put("440300", "深圳市");
			put("440400", "珠海市");
			put("440500", "汕头市");
			put("440600", "佛山市");
			put("440700", "江门市");
			put("440800", "湛江市");
			put("440900", "茂名市");
			put("441200", "肇庆市");
			put("441300", "惠州市");
			put("441400", "梅州市");
			put("441500", "汕尾市");
			put("441600", "河源市");
			put("441700", "阳江市");
			put("441800", "清远市");
			put("441900", "东莞市");
			put("442000", "中山市");
			put("445100", "潮州市");
			put("445200", "揭阳市");
			put("445300", "云浮市");
			put("450100", "南宁市");
			put("450200", "柳州市");
			put("450300", "桂林市");
			put("450400", "梧州市");
			put("450500", "北海市");
			put("450600", "防城港市");
			put("450700", "钦州市");
			put("450800", "贵港市");
			put("450900", "玉林市");
			put("451000", "百色市");
			put("451100", "贺州市");
			put("451200", "河池市");
			put("451300", "来宾市");
			put("451400", "崇左市");
			put("460100", "海口市");
			put("460200", "三亚市");
			put("500200", "涪陵区");
			put("500300", "渝中区");
			put("500400", "大渡口区");
			put("500500", "江北区");
			put("500600", "沙坪坝区");
			put("500700", "九龙坡区");
			put("500800", "南岸区");
			put("500900", "北碚区");
			put("501000", "万盛区");
			put("501100", "双桥区");
			put("501200", "渝北区");
			put("501300", "巴南区");
			put("501400", "黔江区");
			put("501500", "长寿区");
			put("501600", "綦江县");
			put("501700", "潼南县");
			put("501800", "铜梁县");
			put("501900", "大足县");
			put("502000", "荣昌县");
			put("502100", "璧山县");
			put("502200", "梁平县");
			put("502300", "城口县");
			put("502400", "丰都县");
			put("502500", "垫江县");
			put("502600", "武隆县");
			put("502700", "忠　县");
			put("502800", "开　县");
			put("502900", "云阳县");
			put("503000", "奉节县");
			put("503100", "巫山县");
			put("503200", "巫溪县");
			put("503300", "其他");
			put("503400", "江津市");
			put("503500", "合川市");
			put("503600", "永川市");
			put("503700", "南川市");
			put("510100", "成都市");
			put("510300", "自贡市");
			put("510400", "攀枝花市");
			put("510500", "泸州市");
			put("510600", "德阳市");
			put("510700", "绵阳市");
			put("510800", "广元市");
			put("510900", "遂宁市");
			put("511000", "内江市");
			put("511100", "乐山市");
			put("511300", "南充市");
			put("511400", "眉山市");
			put("511500", "宜宾市");
			put("511600", "广安市");
			put("511700", "达州市");
			put("511800", "雅安市");
			put("511900", "巴中市");
			put("512000", "资阳市");
			put("513200", "阿坝州");
			put("513300", "甘孜州");
			put("513400", "凉山州");
			put("520100", "贵阳市");
			put("520200", "六盘水市");
			put("520300", "遵义市");
			put("520400", "安顺市");
			put("522200", "铜仁地区");
			put("522300", "黔西州");
			put("522400", "毕节地区");
			put("522600", "黔东南州");
			put("522700", "黔南州");
			put("530100", "昆明市");
			put("530300", "曲靖市");
			put("530400", "玉溪市");
			put("530500", "保山市");
			put("530600", "昭通市");
			put("530700", "丽江市");
			put("530800", "思茅市");
			put("530900", "临沧市");
			put("532300", "楚雄州");
			put("532500", "红河州");
			put("532600", "文山州");
			put("532800", "西双版纳");
			put("532900", "大理州");
			put("533100", "德宏州");
			put("533300", "怒江州");
			put("533400", "迪庆州");
			put("540100", "拉萨市");
			put("542100", "昌都地区");
			put("542200", "山南地区");
			put("542300", "日喀则");
			put("542400", "那曲地区");
			put("542500", "阿里地区");
			put("542600", "林芝地区");
			put("610100", "西安市");
			put("610200", "铜川市");
			put("610300", "宝鸡市");
			put("610400", "咸阳市");
			put("610500", "渭南市");
			put("610600", "延安市");
			put("610700", "汉中市");
			put("610800", "榆林市");
			put("610900", "安康市");
			put("611000", "商洛市");
			put("620100", "兰州市");
			put("620200", "嘉峪关市");
			put("620300", "金昌市");
			put("620400", "白银市");
			put("620500", "天水市");
			put("620600", "武威市");
			put("620700", "张掖市");
			put("620800", "平凉市");
			put("620900", "酒泉市");
			put("621000", "庆阳市");
			put("621100", "定西市");
			put("621200", "陇南市");
			put("622900", "临夏");
			put("623000", "甘南");
			put("630100", "西宁市");
			put("632100", "海东地区");
			put("632200", "海北");
			put("632300", "黄南");
			put("632500", "海南");
			put("632600", "果洛");
			put("632700", "玉树");
			put("632800", "海西");
			put("640100", "银川市");
			put("640200", "石嘴山市");
			put("640300", "吴忠市");
			put("640400", "固原市");
			put("640500", "中卫市");
			put("650100", "乌鲁木齐市");
			put("650200", "克拉玛依市");
			put("652100", "吐鲁番地区");
			put("652200", "哈密地区");
			put("652300", "昌吉");
			put("652700", "博尔塔拉");
			put("652800", "巴音郭楞");
			put("652900", "阿克苏地区");
			put("653000", "克孜勒");
			put("653100", "喀什地区");
			put("653200", "和田地区");
			put("654000", "伊犁哈萨克");
			put("654200", "塔城地区");
			put("654300", "阿勒泰地区");
		}
	};
}
