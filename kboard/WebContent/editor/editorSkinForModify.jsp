<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="/editor/css/editor.css" type="text/css"/>
<script src="/editor/js/editor_loader.js" type="text/javascript"></script>
<script src="/editor/js/saveContentM.js" defer></script>

<script>
    // 에디터
    var cn_wi = 1500;
    var cn_hi = 500;
    if (window.innerWidth <= 400) {
        cn_wi = 400;
        cn_hi = 330;
    }

    var config = {
        txHost: '', /* 런타임 시 리소스들을 로딩할 때 필요한 부분으로, 경로가 변경되면 이 부분 수정이 필요. ex) http://xxx.xxx.com */
        txPath: '', /* 런타임 시 리소스들을 로딩할 때 필요한 부분으로, 경로가 변경되면 이 부분 수정이 필요. ex) /xxx/xxx/ */
        txService: 'sample', /* 수정필요없음. */
        txProject: 'sample', /* 수정필요없음. 프로젝트가 여러개일 경우만 수정한다. */
        initializedId: "", /* 대부분의 경우에 빈문자열 */
        wrapper: "tx_trex_container", /* 에디터를 둘러싸고 있는 레이어 이름(에디터 컨테이너) */
        form: 'editorForm' + "", /* 등록하기 위한 Form 이름 */
        txIconPath: "/editor/images/icon/editor/", /*에디터에 사용되는 이미지 디렉터리, 필요에 따라 수정한다. */
        txDecoPath: "/editor/images/deco/contents/", /*본문에 사용되는 이미지 디렉터리, 서비스에서 사용할 때는 완성된 컨텐츠로 배포되기 위해 절대경로로 수정한다. */
        canvas: {
            exitEditor: {
                /*
                desc:'빠져 나오시려면 shift+b를 누르세요.',
                hotKey: {
                    shiftKey:true,
                    keyCode:66
                },
                nextElement: document.getElementsByTagName('button')[0]
                */
            },
            initHeight: cn_hi, // 높이
            styles: {
                color: "#123456", /* 기본 글자색 */
                fontFamily: "맑은고딕", /* 기본 글자체 */
                fontSize: "10pt", /* 기본 글자크기 */
                backgroundColor: "#fff", /*기본 배경색 */
                lineHeight: "1.3", /*기본 줄간격 */
                padding: "5px" /* 위지윅 영역의 여백 */
            },
            showGuideArea: true
        },
        events: {
            preventUnload: false
        },
        sidebar: {
            capacity: {maximum: 5000000},
            attacher: {
                image: {
                    features: {left: 500, top: 300, width: 580, height: 186}
                }
            },
            attachbox: {
                show: true,
                confirmForDeleteAll: true
            }
        },
        toolbar: {
            fontfamily: {
                options: [
                    {
                        label: ' 맑은고딕 (<span class="tx-txt">가나다라</span>)',
                        title: '맑은고딕',
                        data: '"맑은 고딕",AppleGothic,sans-serif',
                        klass: 'tx-gulim'
                    },
                    {
                        label: ' 굴림 (<span class="tx-txt">가나다라</span>)',
                        title: '굴림',
                        data: 'Gulim,굴림,AppleGothic,sans-serif',
                        klass: 'tx-gulim'
                    },
                    {
                        label: ' 바탕 (<span class="tx-txt">가나다라</span>)',
                        title: '바탕',
                        data: 'Batang,바탕',
                        klass: 'tx-batang'
                    },
                    {
                        label: ' 돋움 (<span class="tx-txt">가나다라</span>)',
                        title: '돋움',
                        data: 'Dotum,돋움',
                        klass: 'tx-dotum'
                    },
                    {
                        label: ' 궁서 (<span class="tx-txt">가나다라</span>)',
                        title: '궁서',
                        data: 'Gungsuh,궁서',
                        klass: 'tx-gungseo'
                    },
                    {
                        label: ' Arial (<span class="tx-txt">abcde</span>)',
                        title: 'Arial',
                        data: 'Arial',
                        klass: 'tx-arial'
                    },
                    {
                        label: ' Verdana (<span class="tx-txt">abcde</span>)',
                        title: 'Verdana',
                        data: 'Verdana',
                        klass: 'tx-verdana'
                    },
                    {
                        label: ' Arial Black (<span class="tx-txt">abcde</span>)',
                        title: 'Arial Black',
                        data: 'Arial Black',
                        klass: 'tx-arial-black'
                    },
                    {
                        label: ' Book Antiqua (<span class="tx-txt">abcde</span>)',
                        title: 'Book Antiqua',
                        data: 'Book Antiqua',
                        klass: 'tx-book-antiqua'
                    },
                    {
                        label: ' Comic Sans MS (<span class="tx-txt">abcde</span>)',
                        title: 'Comic Sans MS',
                        data: 'Comic Sans MS',
                        klass: 'tx-comic-sans-ms'
                    },
                    {
                        label: ' Courier New (<span class="tx-txt">abcde</span>)',
                        title: 'Courier New',
                        data: 'Courier New',
                        klass: 'tx-courier-new'
                    },
                    {
                        label: ' Georgia (<span class="tx-txt">abcde</span>)',
                        title: 'Georgia',
                        data: 'Georgia',
                        klass: 'tx-georgia'
                    },
                    {
                        label: ' Helvetica (<span class="tx-txt">abcde</span>)',
                        title: 'Helvetica',
                        data: 'Helvetica',
                        klass: 'tx-helvetica'
                    },
                    {
                        label: ' Impact (<span class="tx-txt">abcde</span>)',
                        title: 'Impact',
                        data: 'Impact',
                        klass: 'tx-impact'
                    },
                    {
                        label: ' Symbol (<span class="tx-txt">abcde</span>)',
                        title: 'Symbol',
                        data: 'Symbol',
                        klass: 'tx-symbol'
                    },
                    {
                        label: ' Tahoma (<span class="tx-txt">abcde</span>)',
                        title: 'Tahoma',
                        data: 'Tahoma',
                        klass: 'tx-tahoma'
                    },
                    {
                        label: ' Terminal (<span class="tx-txt">abcde</span>)',
                        title: 'Terminal',
                        data: 'Terminal',
                        klass: 'tx-terminal'
                    },
                    {
                        label: ' Times New Roman (<span class="tx-txt">abcde</span>)',
                        title: 'Times New Roman',
                        data: 'Times New Roman',
                        klass: 'tx-times-new-roman'
                    },
                    {
                        label: ' Trebuchet MS (<span class="tx-txt">abcde</span>)',
                        title: 'Trebuchet MS',
                        data: 'Trebuchet MS',
                        klass: 'tx-trebuchet-ms'
                    },
                    {
                        label: ' Webdings (<span class="tx-txt">abcde</span>)',
                        title: 'Webdings',
                        data: 'Webdings',
                        klass: 'tx-webdings'
                    },
                    {
                        label: ' Wingdings (<span class="tx-txt">abcde</span>)',
                        title: 'Wingdings',
                        data: 'Wingdings',
                        klass: 'tx-wingdings'
                    }
                ]
            }
        },
        size: {
            contentWidth: cn_wi /* 지정된 본문영역의 넓이가 있을 경우에 설정 */
        }
    };
</script>
<!-- 에디터 시작 -->
<!--@decsription
등록하기 위한 Form으로 상황에 맞게 수정하여 사용한다. Form 이름은 에디터를 생성할 때 설정값으로 설정한다.-->
<div class="ad_rp_editor_wrap">
    <table align="left" class="book">
        <tr align="center">
            <td class="ad_rp_editor" colspan="3">
                <!-- 에디터 컨테이너 시작 -->
                <div id="tx_trex_container" class="tx-editor-container">
                    <!-- 사이드바 -->
                    <div id="tx_sidebar" class="tx-sidebar">
                        <div class="tx-sidebar-boundary">
                            <!-- 사이드바 / 첨부 -->
                            <ul class="tx-bar tx-bar-left tx-nav-attach">
                                <!-- 이미지 첨부 버튼 시작 -->
                                <!--
                                    @decsription
                                    <li></li> 단위로 위치를 이동할 수 있다.
        -->
                                <li class="tx-list">
                                    <div unselectable="on" id="tx_image"
                                         class="tx-image tx-btn-trans">
                                        <a href="javascript:;" title="사진"
                                           class="tx-text">사진</a>
                                    </div>
                                </li>
                                <!-- 이미지 첨부 버튼 끝 -->
                                <li class="tx-list">
                                    <div unselectable="on" id="tx_media"
                                         class="tx-media tx-btn-trans">
                                        <a href="javascript:;" title="외부컨텐츠"
                                           class="tx-text">외부컨텐츠</a>
                                    </div>
                                </li>
                            </ul>
                            <!-- 사이드바 / 우측영역 -->
                            <ul class="tx-bar tx-bar-right tx-nav-opt">
                                <li class="tx-list">
                                    <div unselectable="on"
                                         class="tx-switchtoggle"
                                         id="tx_switchertoggle">
                                        <a href="javascript:;" title="에디터 타입">에디터</a>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>

                    <!-- 툴바 - 기본 시작 -->
                    <!--
                        @decsription
                        툴바 버튼의 그룹핑의 변경이 필요할 때는 위치(왼쪽, 가운데, 오른쪽) 에 따라 <li> 아래의 <div>의 클래스명을 변경하면 된다.
        tx-btn-lbg: 왼쪽, tx-btn-bg: 가운데, tx-btn-rbg: 오른쪽, tx-btn-lrbg: 독립적인 그룹

                        드롭다운 버튼의 크기를 변경하고자 할 경우에는 넓이에 따라 <li> 아래의 <div>의 클래스명을 변경하면 된다.
        tx-slt-70bg, tx-slt-59bg, tx-slt-42bg, tx-btn-43lrbg, tx-btn-52lrbg, tx-btn-57lrbg, tx-btn-71lrbg
                        tx-btn-48lbg, tx-btn-48rbg, tx-btn-30lrbg, tx-btn-46lrbg, tx-btn-67lrbg, tx-btn-49lbg, tx-btn-58bg, tx-btn-46bg, tx-btn-49rbg
        -->
                    <div id="tx_toolbar_basic"
                         class="tx-toolbar tx-toolbar-basic">
                        <div class="tx-toolbar-boundary">
                            <ul class="tx-bar tx-bar-left">
                                <li class="tx-list">
                                    <div id="tx_fontfamily" unselectable="on"
                                         class="tx-slt-70bg tx-fontfamily">
                                        <a href="javascript:;" title="글꼴">굴림</a>
                                    </div>
                                    <div id="tx_fontfamily_menu"
                                         class="tx-fontfamily-menu tx-menu"
                                         unselectable="on"></div>
                                </li>
                            </ul>
                            <ul class="tx-bar tx-bar-left">
                                <li class="tx-list">
                                    <div unselectable="on"
                                         class="tx-slt-42bg tx-fontsize"
                                         id="tx_fontsize">
                                        <a href="javascript:;"
                                           title="글자크기">9pt</a>
                                    </div>
                                    <div id="tx_fontsize_menu"
                                         class="tx-fontsize-menu tx-menu"
                                         unselectable="on"></div>
                                </li>
                            </ul>
                            <ul class="tx-bar tx-bar-left tx-group-font">

                                <li class="tx-list">
                                    <div unselectable="on"
                                         class="tx-btn-lbg tx-bold"
                                         id="tx_bold">
                                        <a href="javascript:;" class="tx-icon"
                                           title="굵게 (Ctrl+B)">굵게</a>
                                    </div>
                                </li>
                                <li class="tx-list">
                                    <div unselectable="on"
                                         class="tx-btn-bg tx-underline"
                                         id="tx_underline">
                                        <a href="javascript:;" class="tx-icon"
                                           title="밑줄 (Ctrl+U)">밑줄</a>
                                    </div>
                                </li>
                                <li class="tx-list">
                                    <div unselectable="on"
                                         class="tx-btn-bg tx-italic"
                                         id="tx_italic">
                                        <a href="javascript:;" class="tx-icon"
                                           title="기울임 (Ctrl+I)">기울임</a>
                                    </div>
                                </li>
                                <li class="tx-list">
                                    <div unselectable="on"
                                         class="tx-btn-bg tx-strike"
                                         id="tx_strike">
                                        <a href="javascript:;" class="tx-icon"
                                           title="취소선 (Ctrl+D)">취소선</a>
                                    </div>
                                </li>
                                <li class="tx-list">
                                    <div unselectable="on"
                                         class="tx-slt-tbg tx-forecolor"
                                         id="tx_forecolor">
                                        <a href="javascript:;" class="tx-icon"
                                           title="글자색">글자색</a>
                                        <a href="javascript:;" class="tx-arrow"
                                           title="글자색 선택">글자색 선택</a>
                                    </div>
                                    <div id="tx_forecolor_menu"
                                         class="tx-menu tx-forecolor-menu tx-colorpallete"
                                         unselectable="on"></div>
                                </li>
                                <li class="tx-list">
                                    <div unselectable="on"
                                         class="tx-slt-brbg 	tx-backcolor"
                                         id="tx_backcolor">
                                        <a href="javascript:;" class="tx-icon"
                                           title="글자 배경색">글자 배경색</a>
                                        <a href="javascript:;" class="tx-arrow"
                                           title="글자 배경색 선택">글자 배경색
                                            선택</a>
                                    </div>
                                    <div id="tx_backcolor_menu"
                                         class="tx-menu tx-backcolor-menu tx-colorpallete"
                                         unselectable="on"></div>
                                </li>
                            </ul>
                            <ul class="tx-bar tx-bar-left tx-group-align">
                                <li class="tx-list">
                                    <div unselectable="on"
                                         class="tx-btn-lbg tx-alignleft"
                                         id="tx_alignleft">
                                        <a href="javascript:;" class="tx-icon"
                                           title="왼쪽정렬 (Ctrl+,)">왼쪽정렬</a>
                                    </div>
                                </li>
                                <li class="tx-list">
                                    <div unselectable="on"
                                         class="tx-btn-bg tx-aligncenter"
                                         id="tx_aligncenter">
                                        <a href="javascript:;" class="tx-icon"
                                           title="가운데정렬 (Ctrl+.)">가운데정렬</a>
                                    </div>
                                </li>
                                <li class="tx-list">
                                    <div unselectable="on"
                                         class="tx-btn-bg tx-alignright"
                                         id="tx_alignright">
                                        <a href="javascript:;" class="tx-icon"
                                           title="오른쪽정렬 (Ctrl+/)">오른쪽정렬</a>
                                    </div>
                                </li>
                                <li class="tx-list">
                                    <div unselectable="on"
                                         class="tx-btn-rbg tx-alignfull"
                                         id="tx_alignfull">
                                        <a href="javascript:;" class="tx-icon"
                                           title="양쪽정렬">양쪽정렬</a>
                                    </div>
                                </li>
                            </ul>
                            <ul class="tx-bar tx-bar-left tx-group-tab">
                                <li class="tx-list">
                                    <div unselectable="on"
                                         class="tx-btn-lbg tx-indent"
                                         id="tx_indent">
                                        <a href="javascript:;"
                                           title="들여쓰기 (Tab)"
                                           class="tx-icon">들여쓰기</a>
                                    </div>
                                </li>
                                <li class="tx-list">
                                    <div unselectable="on"
                                         class="tx-btn-rbg tx-outdent"
                                         id="tx_outdent">
                                        <a href="javascript:;"
                                           title="내어쓰기 (Shift+Tab)"
                                           class="tx-icon">내어쓰기</a>
                                    </div>
                                </li>
                            </ul>
                            <ul class="tx-bar tx-bar-left tx-group-list">
                                <li class="tx-list">
                                    <div unselectable="on"
                                         class="tx-slt-31lbg tx-lineheight"
                                         id="tx_lineheight">
                                        <a href="javascript:;" class="tx-icon"
                                           title="줄간격">줄간격</a>
                                        <a href="javascript:;" class="tx-arrow"
                                           title="줄간격">줄간격
                                            선택</a>
                                    </div>
                                    <div id="tx_lineheight_menu"
                                         class="tx-lineheight-menu tx-menu"
                                         unselectable="on"></div>
                                </li>
                                <li class="tx-list">
                                    <div unselectable="on"
                                         class="tx-slt-31rbg tx-styledlist"
                                         id="tx_styledlist">
                                        <a href="javascript:;" class="tx-icon"
                                           title="리스트">리스트</a>
                                        <a href="javascript:;" class="tx-arrow"
                                           title="리스트">리스트
                                            선택</a>
                                    </div>
                                    <div id="tx_styledlist_menu"
                                         class="tx-styledlist-menu tx-menu"
                                         unselectable="on"></div>
                                </li>
                            </ul>
                            <ul class="tx-bar tx-bar-left tx-group-etc">
                                <li class="tx-list">
                                    <div unselectable="on"
                                         class="tx-btn-lbg tx-emoticon"
                                         id="tx_emoticon">
                                        <a href="javascript:;" class="tx-icon"
                                           title="이모티콘">이모티콘</a>
                                    </div>
                                    <div id="tx_emoticon_menu"
                                         class="tx-emoticon-menu tx-menu"
                                         unselectable="on"></div>
                                </li>
                                <li class="tx-list">
                                    <div unselectable="on"
                                         class="tx-btn-bg tx-link"
                                         id="tx_link">
                                        <a href="javascript:;" class="tx-icon"
                                           title="링크 (Ctrl+K)">링크</a>
                                    </div>
                                    <div id="tx_link_menu"
                                         class="tx-link-menu tx-menu"></div>
                                </li>
                                <li class="tx-list">
                                    <div unselectable="on"
                                         class="tx-btn-bg tx-specialchar"
                                         id="tx_specialchar">
                                        <a href="javascript:;" class="tx-icon"
                                           title="특수문자">특수문자</a>
                                    </div>
                                    <div id="tx_specialchar_menu"
                                         class="tx-specialchar-menu tx-menu"></div>
                                </li>
                                <li class="tx-list">
                                    <div unselectable="on"
                                         class="tx-btn-bg tx-table"
                                         id="tx_table">
                                        <a href="javascript:;" class="tx-icon"
                                           title="표만들기">표만들기</a>
                                    </div>
                                    <div id="tx_table_menu"
                                         class="tx-table-menu tx-menu"
                                         unselectable="on">
                                        <div class="tx-menu-inner">
                                            <div class="tx-menu-preview"></div>
                                            <div class="tx-menu-rowcol"></div>
                                            <div class="tx-menu-deco"></div>
                                            <div class="tx-menu-enter"></div>
                                        </div>
                                    </div>
                                </li>
                                <li class="tx-list">
                                    <div unselectable="on"
                                         class="tx-btn-rbg tx-horizontalrule"
                                         id="tx_horizontalrule">
                                        <a href="javascript:;" class="tx-icon"
                                           title="구분선">구분선</a>
                                    </div>
                                    <div id="tx_horizontalrule_menu"
                                         class="tx-horizontalrule-menu tx-menu"
                                         unselectable="on"></div>
                                </li>
                            </ul>
                            <ul class="tx-bar tx-bar-left">
                                <li class="tx-list">
                                    <div unselectable="on"
                                         class="tx-btn-lbg tx-richtextbox"
                                         id="tx_richtextbox">
                                        <a href="javascript:;" class="tx-icon"
                                           title="글상자">글상자</a>
                                    </div>
                                    <div id="tx_richtextbox_menu"
                                         class="tx-richtextbox-menu tx-menu">
                                        <div class="tx-menu-header">
                                            <div class="tx-menu-preview-area">
                                                <div class="tx-menu-preview"></div>
                                            </div>
                                            <div class="tx-menu-switch">
                                                <div class="tx-menu-simple tx-selected">
                                                    <a><span>간단 선택</span></a>
                                                </div>
                                                <div class="tx-menu-advanced">
                                                    <a><span>직접 선택</span></a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="tx-menu-inner">
                                        </div>
                                        <div class="tx-menu-footer">
                                            <img class="tx-menu-confirm"
                                                 src="/editor/images/icon/editor/btn_confirm.gif?rv=1.0.1"
                                                 alt=""/>
                                            <img class="tx-menu-cancel"
                                                 hspace="3"
                                                 src="/editor/images/icon/editor/btn_cancel.gif?rv=1.0.1"
                                                 alt=""/>
                                        </div>
                                    </div>
                                </li>
                                <li class="tx-list">
                                    <div unselectable="on"
                                         class="tx-btn-bg tx-quote"
                                         id="tx_quote">
                                        <a href="javascript:;" class="tx-icon"
                                           title="인용구 (Ctrl+Q)">인용구</a>
                                    </div>
                                    <div id="tx_quote_menu"
                                         class="tx-quote-menu tx-menu"
                                         unselectable="on"></div>
                                </li>
                                <li class="tx-list">
                                    <div unselectable="on"
                                         class="tx-btn-bg tx-background"
                                         id="tx_background">
                                        <a href="javascript:;" class="tx-icon"
                                           title="배경색">배경색</a>
                                    </div>
                                    <div id="tx_background_menu"
                                         class="tx-menu tx-background-menu tx-colorpallete"
                                         unselectable="on"></div>
                                </li>
                                <li class="tx-list">
                                    <div unselectable="on"
                                         class="tx-btn-rbg tx-dictionary"
                                         id="tx_dictionary">
                                        <a href="javascript:;" class="tx-icon"
                                           title="사전">사전</a>
                                    </div>
                                </li>
                            </ul>
                            <ul class="tx-bar tx-bar-left tx-group-undo">
                                <li class="tx-list">
                                    <div unselectable="on"
                                         class="tx-btn-lbg tx-undo"
                                         id="tx_undo">
                                        <a href="javascript:;" class="tx-icon"
                                           title="실행취소 (Ctrl+Z)">실행취소</a>
                                    </div>
                                </li>
                                <li class="tx-list">
                                    <div unselectable="on"
                                         class="tx-btn-rbg tx-redo"
                                         id="tx_redo">
                                        <a href="javascript:;" class="tx-icon"
                                           title="다시실행 (Ctrl+Y)">다시실행</a>
                                    </div>
                                </li>
                            </ul>
                            <ul class="tx-bar tx-bar-right">
                                <li class="tx-list">
                                    <div unselectable="on"
                                         class="tx-btn-nlrbg tx-advanced"
                                         id="tx_advanced">
                                        <a href="javascript:;" class="tx-icon"
                                           title="툴바 더보기">툴바 더보기</a>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <!-- 툴바 - 기본 끝 -->
                    <!-- 툴바 - 더보기 시작 -->
                    <div id="tx_toolbar_advanced"
                         class="tx-toolbar tx-toolbar-advanced">
                        <div class="tx-toolbar-boundary">
                            <ul class="tx-bar tx-bar-left">
                                <li class="tx-list">
                                    <div class="tx-tableedit-title"></div>
                                </li>
                            </ul>

                            <ul class="tx-bar tx-bar-left tx-group-align">
                                <li class="tx-list">
                                    <div unselectable="on"
                                         class="tx-btn-lbg tx-mergecells"
                                         id="tx_mergecells">
                                        <a href="javascript:;" class="tx-icon2"
                                           title="병합">병합</a>
                                    </div>
                                    <div id="tx_mergecells_menu"
                                         class="tx-mergecells-menu tx-menu"
                                         unselectable="on"></div>
                                </li>
                                <li class="tx-list">
                                    <div unselectable="on"
                                         class="tx-btn-bg tx-insertcells"
                                         id="tx_insertcells">
                                        <a href="javascript:;" class="tx-icon2"
                                           title="삽입">삽입</a>
                                    </div>
                                    <div id="tx_insertcells_menu"
                                         class="tx-insertcells-menu tx-menu"
                                         unselectable="on"></div>
                                </li>
                                <li class="tx-list">
                                    <div unselectable="on"
                                         class="tx-btn-rbg tx-deletecells"
                                         id="tx_deletecells">
                                        <a href="javascript:;" class="tx-icon2"
                                           title="삭제">삭제</a>
                                    </div>
                                    <div id="tx_deletecells_menu"
                                         class="tx-deletecells-menu tx-menu"
                                         unselectable="on"></div>
                                </li>
                            </ul>

                            <ul class="tx-bar tx-bar-left tx-group-align">
                                <li class="tx-list">
                                    <div id="tx_cellslinepreview"
                                         unselectable="on"
                                         class="tx-slt-70lbg tx-cellslinepreview">
                                        <a href="javascript:;"
                                           title="선 미리보기"></a>
                                    </div>
                                    <div id="tx_cellslinepreview_menu"
                                         class="tx-cellslinepreview-menu tx-menu"
                                         unselectable="on"></div>
                                </li>
                                <li class="tx-list">
                                    <div id="tx_cellslinecolor"
                                         unselectable="on"
                                         class="tx-slt-tbg tx-cellslinecolor">
                                        <a href="javascript:;" class="tx-icon2"
                                           title="선색">선색</a>

                                        <div class="tx-colorpallete"
                                             unselectable="on"></div>
                                    </div>
                                    <div id="tx_cellslinecolor_menu"
                                         class="tx-cellslinecolor-menu tx-menu tx-colorpallete"
                                         unselectable="on"></div>
                                </li>
                                <li class="tx-list">
                                    <div id="tx_cellslineheight"
                                         unselectable="on"
                                         class="tx-btn-bg tx-cellslineheight">
                                        <a href="javascript:;" class="tx-icon2"
                                           title="두께">두께</a>

                                    </div>
                                    <div id="tx_cellslineheight_menu"
                                         class="tx-cellslineheight-menu tx-menu"
                                         unselectable="on"></div>
                                </li>
                                <li class="tx-list">
                                    <div id="tx_cellslinestyle"
                                         unselectable="on"
                                         class="tx-btn-bg tx-cellslinestyle">
                                        <a href="javascript:;" class="tx-icon2"
                                           title="스타일">스타일</a>
                                    </div>
                                    <div id="tx_cellslinestyle_menu"
                                         class="tx-cellslinestyle-menu tx-menu"
                                         unselectable="on"></div>
                                </li>
                                <li class="tx-list">
                                    <div id="tx_cellsoutline" unselectable="on"
                                         class="tx-btn-rbg tx-cellsoutline">
                                        <a href="javascript:;" class="tx-icon2"
                                           title="테두리">테두리</a>

                                    </div>
                                    <div id="tx_cellsoutline_menu"
                                         class="tx-cellsoutline-menu tx-menu"
                                         unselectable="on"></div>
                                </li>
                            </ul>
                            <ul class="tx-bar tx-bar-left">
                                <li class="tx-list">
                                    <div id="tx_tablebackcolor"
                                         unselectable="on"
                                         class="tx-btn-lrbg tx-tablebackcolor"
                                         style="background-color:#9aa5ea;">
                                        <a href="javascript:;" class="tx-icon2"
                                           title="테이블 배경색">테이블 배경색</a>
                                    </div>
                                    <div id="tx_tablebackcolor_menu"
                                         class="tx-tablebackcolor-menu tx-menu tx-colorpallete"
                                         unselectable="on"></div>
                                </li>
                            </ul>
                            <ul class="tx-bar tx-bar-left">
                                <li class="tx-list">
                                    <div id="tx_tabletemplate" unselectable="on"
                                         class="tx-btn-lrbg tx-tabletemplate">
                                        <a href="javascript:;" class="tx-icon2"
                                           title="테이블 서식">테이블 서식</a>
                                    </div>
                                    <div id="tx_tabletemplate_menu"
                                         class="tx-tabletemplate-menu tx-menu tx-colorpallete"
                                         unselectable="on"></div>
                                </li>
                            </ul>

                        </div>
                    </div>
                    <!-- 툴바 - 더보기 끝 -->
                    <!-- 편집영역 시작 -->
                    <!-- 에디터 Start -->
                    <div id="tx_canvas" class="tx-canvas">
                        <div id="tx_loading" class="tx-loading">
                            <div><img
                                    src="/editor/images/icon/editor/loading2.png"
                                    width="113"
                                    height="21"
                                    align="absmiddle"/></div>
                        </div>
                        <div id="tx_canvas_wysiwyg_holder" class="tx-holder"
                             style="display:block;">
                            <iframe id="tx_canvas_wysiwyg"
                                    name="tx_canvas_wysiwyg"
                                    allowtransparency="true"
                                    frameborder="0"></iframe>
                        </div>
                        <div class="tx-source-deco">
                            <div id="tx_canvas_source_holder" class="tx-holder">
                                <textarea id="tx_canvas_source"></textarea>
                            </div>
                        </div>
                        <div id="tx_canvas_text_holder" class="tx-holder">
                            <textarea id="tx_canvas_text"></textarea>
                        </div>
                    </div>
                    <!-- 높이조절 Start -->
                    <div id="tx_resizer" class="tx-resize-bar">
                        <div class="tx-resize-bar-bg"></div>
                        <img id="tx_resize_holder"
                             src="/editor/images/icon/editor/skin/01/btn_drag01.gif"
                             width="58" height="12" unselectable="on" alt=""/>
                    </div>
                    <!-- 편집영역 끝 -->
                    <!-- 첨부박스 시작 -->
                    <!-- 파일첨부박스 Start -->
                    <div id="tx_attach_div" class="tx-attach-div">
                        <div id="tx_attach_txt" class="tx-attach-txt">파일 첨부
                        </div>
                        <div id="tx_attach_box" class="tx-attach-box">
                            <div class="tx-attach-box-inner">
                                <div id="tx_attach_preview"
                                     class="tx-attach-preview">
                                    <p></p><img
                                        src="/editor/images/icon/editor/pn_preview.gif"
                                        width="147"
                                        height="108" unselectable="on"/></div>
                                <div class="tx-attach-main">
                                    <div id="tx_upload_progress"
                                         class="tx-upload-progress">
                                        <div>0%</div>
                                        <p>파일을 업로드하는 중입니다.</p></div>
                                    <ul class="tx-attach-top">
                                        <li id="tx_attach_delete"
                                            class="tx-attach-delete">
                                            <a>전체삭제</a></li>
                                        <li id="tx_attach_size"
                                            class="tx-attach-size">
                                            파일: <span id="tx_attach_up_size"
                                                      class="tx-attach-size-up"></span>/<span
                                                id="tx_attach_max_size"></span>
                                        </li>
                                        <li id="tx_attach_tools"
                                            class="tx-attach-tools">
                                        </li>
                                    </ul>
                                    <ul id="tx_attach_list"
                                        class="tx-attach-list"></ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 첨부박스 끝 -->
                </div>
                <!-- 에디터 컨테이너 끝 -->
            </td>
        </tr>
    </table>
</div>
