# Internet_Service_Applications
simple pet-owner system in java

## Monolith     
-> monolith application with both pet and owner entities managed in one project in the same database            

## Gateway         
-> gateway application providing communication between separate projects with separate databases owners and pets on different ports          

## owners
-> project managing owner entities and requests directed at owners        

## pets        
-> project managing pet entities and requests directed at pets, with simple owner implementation to maintain link between entities (one to many)          
