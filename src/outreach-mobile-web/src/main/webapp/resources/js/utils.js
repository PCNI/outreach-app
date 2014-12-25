/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 function configDate(){
        $( ".dateClass").datepicker({
                        yearRange: "1930:2010"
        });

        var yearRange = $( ".dateClass" ).datepicker( "option", "yearRange" );
        $( ".dateClass" ).datepicker( "option", "yearRange", "1930:2010" );

        var changeYear = $( ".dateClass" ).datepicker( "option", "changeYear" );
        $( ".dateClass" ).datepicker( "option", "changeYear", true );			

        $( ".dateClass").datepicker({   
                dateFormat: "mm/dd/yy"
        });

        var dateFormat = $( ".dateClass" ).datepicker( "option", "dateFormat" );
        // Setter
        $( ".dateClass" ).datepicker( "option", "dateFormat", "mm/dd/yy" );
 }
