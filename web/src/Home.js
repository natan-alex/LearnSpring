import {useEffect, useState} from 'react';

function Home() {
    let [customers, setCustomers] = useState([])

    async function fetchCustomerData() {
        let response = await fetch('http://localhost:8080/customers')
        let data = await response.json()
        setCustomers(data)
        console.log(customers)
    }

    useEffect(() => {
        fetchCustomerData()
    }, [])

    return <>
        <div>
            { customers.map( customer => 
                <div key={customer.id}>
                    <p>customer id: {customer.id}</p>
                    <p>customer name: {customer.name}</p>
                </div>
            ) }
        </div>
    </>
}


export default Home;