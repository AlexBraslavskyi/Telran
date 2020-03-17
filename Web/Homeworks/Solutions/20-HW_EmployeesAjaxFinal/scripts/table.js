class Table {
    constructor(keys, trHeadSelector, tbodySelector, keyId,
                removeFn){
        this.keys = keys;
        this.$tbodyElement = $(tbodySelector);
        this.keyId = keyId;
        this.removeFn = removeFn;
        const $trHeadElement = $(trHeadSelector);
        keys.forEach(function(key) {
            const $thElement =$('<th>',{
                text: key
            });
            $trHeadElement.append($thElement);

        })
        if (removeFn) {
            $trHeadElement.append($('<th>', {
                // text: 'Remove'
            }))
        }
    }
    addRow(obj) {
        const $trElement = $('<tr>');
        this.$tbodyElement.append($trElement);
        this.keys.forEach(function(key){
            const $tdElement = $('<td>', {
                text: obj[key]
            });
            $trElement.append($tdElement);
        })//filling data
        //adding button remove
        /**********************/
        if (this.removeFn) {
            const $buttonElement = $('<button>', {
                text: 'Remove'
            });
            $trElement.append($buttonElement);
            $buttonElement.on('click', async function(event) {
                if (confirm(`you are going
                 to remove ${obj[this.keyId]}`)) {
                    const errorMessage = await this.removeFn(obj[this.keyId]);
                    if (errorMessage == '') {
                        $trElement.remove();
                    } else {
                        alert(errorMessage);
                    }

                }
            }.bind(this))
        }

        /**********************/
    }
    clear() {
        this.$tbodyElement.empty();

    }
}
