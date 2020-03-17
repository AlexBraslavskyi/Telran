// module for creating object based on data from
// form and performing any processing
class FormHandler {
    constructor(selector) {
        this.$formElement = $(selector);
        if (!this.$formElement.length) {
            throw new Error(`wrong selector: ${selector}`)
        }

    }

    addHandler(fnProcessor) {
        this.$formElement.on('submit',
            async function (event) {
                event.preventDefault();
                const objDataArray =
                    this.$formElement.serializeArray();
                const objData = objDataArray
                    .reduce(function (objResult, current) {
                        objResult[current.name] = current.value;
                        return objResult;
                    }, {})
                const message = await fnProcessor(objData);

                if (message) {
                    //something wrong
                    //message contains what is wrong
                    alert(message);
                } else {
                    //everything is ok
                    //performing form reset
                    event.target.reset();
                }


            }.bind(this))
    }
}
