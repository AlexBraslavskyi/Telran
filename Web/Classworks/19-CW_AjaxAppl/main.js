const url = 'http://localhost:3000/orders/';
 function addOrder(order) {
  return $.ajax({
    url: url,
      type: 'POST',
      contentType: 'application/json',
      data: JSON.stringify(order)
  })
}
async function addOrderMain(order) {
     try {
         const res = await addOrder(order);
         console.log(res);
     } catch(error) {
         if (error.readyState) {
             alert(`order with ${order.email} already exists`)
         } else {
             alert('server is unavailable, please retry again')
         }

    }


}
addOrderMain({email: "1235@co.il", coffee: 'espresso',
size: 'tall', flavor: 'none', strength: 50});
function deleteOrder(email) {
    return $.ajax({
        url: url + encodeURIComponent(email),
        type: 'DELETE'

    })
}
//deleteOrder('1235@co.il');
