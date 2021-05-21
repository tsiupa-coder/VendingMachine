# VendingMachine

the dist folder contains .jar

use - git clone https://github.com/tsiupa-coder/VendingMachine.git to download 

requirements: jre 1.8.0_281 or higher

to run the program use : java -jar SnackVendingMachine.jar

List of commands:

1) addCategory "name category" price number (addCategory "chips" 12.50 20) - add new category
2) addItem "name category" number (addItem "chips" 20) - add items to existing category
3) purchase "name category" date (purchase "chips" 2020-12-16) - purchase one item from existing category
4) list  - print list of iesting categories
5) clear - clear panel, after this command list dont show existing category that have 0 item
6) report date (report 2020-04-12) (report 2020-04) - print report about purchase items
