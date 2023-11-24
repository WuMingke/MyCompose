# MyCompose
start compose

Surface: Modifier.clip 之后 没有阴影了 elevation 无效
组件的属性最后也是被合并到Modifier中的，然后Modifier属性的应用，都是从右边执行到左边，左边的有效。