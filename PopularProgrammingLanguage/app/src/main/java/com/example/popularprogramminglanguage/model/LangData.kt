package com.example.popularprogramminglanguage.model

object LangData {
    private var data = arrayOf(
        arrayOf(
            "JavaScript",
            "Most Popular Programming Language by StackOverFlow, \nPopular rank 1",
            "https://seeklogo.com/images/J/javascript-js-logo-2949701702-seeklogo.com.png",
            """ 
                often abbreviated as JS, is a programming language that conforms to the ECMAScript specification.[7] JavaScript is high-level, often just-in-time compiled, and multi-paradigm. It has curly-bracket syntax, dynamic typing, prototype-based object-orientation, and first-class functions.
                Alongside HTML and CSS, JavaScript is one of the core technologies of the World Wide Web.[8] JavaScript enables interactive web pages and is an essential part of web applications. The vast majority of websites use it for client-side page behavior,[9] and all major web browsers have a dedicated JavaScript engine to execute it.
                As a multi-paradigm language, JavaScript supports event-driven, functional, and imperative programming styles. It has application programming interfaces (APIs) for working with text, dates, regular expressions, standard data structures, and the Document Object Model (DOM). However, the language itself does not include any input/output (I/O), such as networking, storage, or graphics facilities, as the host environment (usually a web browser) provides those APIs.
            """.trimIndent(),
            "Branden Eich\n" +
                    "December 4, 1995\n" +
                    "multi-paradigm\n"+
                    ".js\n"
        ),
        arrayOf(
            "HTML (Hypertext Markup Language)",
            "Most Popular Programming Language by StackOverFlow, \nPopular rank 2",
            "https://seeklogo.com/images/H/html5-logo-EF92D240D7-seeklogo.com.png",
            """
                 is the standard markup language for documents designed to be displayed in a web browser. It can be assisted by technologies such as Cascading Style Sheets (CSS) and scripting languages such as JavaScript.
                 Web browsers receive HTML documents from a web server or from local storage and render the documents into multimedia web pages. HTML describes the structure of a web page semantically and originally included cues for the appearance of the document.
                 HTML can embed programs written in a scripting language such as JavaScript, which affects the behavior and content of web pages. Inclusion of CSS defines the look and layout of content. The World Wide Web Consortium (W3C), former maintainer of the HTML and current maintainer of the CSS standards, has encouraged the use of CSS over explicit presentational HTML since 1997.
            """.trimIndent(),
            "WHATWG\n" +
                    "1993\n" +
                    "-\n"+
                    ".html / .htm\n"
        ),
        arrayOf(
            "SQL (Structured Query Language)",
            "Most Popular Programming Language by StackOverFlow, \nPopular rank 3",
            "https://w7.pngwing.com/pngs/28/601/png-transparent-sql-logo-illustration-microsoft-azure-sql-database-microsoft-sql-server-database-blue-text-logo-thumbnail.png",
            """
                is a domain-specific language used in programming and designed for managing data held in a relational database management system (RDBMS), or for stream processing in a relational data stream management system (RDSMS). It is particularly useful in handling structured data, i.e. data incorporating relations among entities and variables.
                SQL offers two main advantages over older read–write APIs such as ISAM or VSAM. Firstly, it introduced the concept of accessing many records with one single command. Secondly, it eliminates the need to specify how to reach a record, e.g. with or without an index.
                SQL was one of the first commercial languages to utilize Edgar F. Codd’s relational model. The model was described in his influential 1970 paper, "A Relational Model of Data for Large Shared Data Banks".[10] Despite not entirely adhering to the relational model as described by Codd, it became the most widely used database language.
                SQL became a standard of the American National Standards Institute (ANSI) in 1986, and of the International Organization for Standardization (ISO) in 1987.[13] Since then the standard has been revised to include a larger set of features. Despite the existence of standards, most SQL code requires at least some changes before being ported to different database systems.
            """.trimIndent(),
            "Donald D. Chamberlin & Raymond\n" +
                    "1974\n" +
                    "Declarative\n" +
                    "-\n"
        ),
        arrayOf(
            "Python",
            "Most Popular Programming Language by StackOverFlow, \nPopular rank 4",
            "https://seeklogo.com/images/P/python-logo-A32636CAA3-seeklogo.com.png",
            """
                 is an interpreted, high-level, general-purpose programming language. Created by Guido van Rossum and first released in 1991, Python's design philosophy emphasizes code readability with its notable use of significant whitespace. Its language constructs and object-oriented approach aim to help programmers write clear, logical code for small and large-scale projects.
                 Python is dynamically typed and garbage-collected. It supports multiple programming paradigms, including structured (particularly, procedural), object-oriented, and functional programming. Python is often described as a "batteries included" language due to its comprehensive standard library
                 Python interpreters are available for many operating systems. A global community of programmers develops and maintains CPython, an open source[34] reference implementation. A non-profit organization, the Python Software Foundation, manages and directs resources for Python and CPython development.
            """.trimIndent(),
            "Guido van Rossum\n" +
                    "1991\n" +
                    "Multi Paradigm\n" +
                    ".py, .pyi, .pyc \n"
        ),
        arrayOf(
            "Java",
            "Most Popular Programming Language by StackOverFlow, \nPopular rank 5",
            "https://upload.wikimedia.org/wikipedia/en/thumb/3/30/Java_programming_language_logo.svg/234px-Java_programming_language_logo.svg.png",
            """
                Java is a general-purpose programming language that is class-based, object-oriented, and designed to have as few implementation dependencies as possible. It is intended to let application developers write once, run anywhere (WORA),[17] meaning that compiled Java code can run on all platforms that support Java without the need for recompilation.
                Java applications are typically compiled to bytecode that can run on any Java virtual machine (JVM) regardless of the underlying computer architecture. The syntax of Java is similar to C and C++, but it has fewer low-level facilities than either of them. As of 2019, Java was one of the most popular programming languages in use according to GitHub, particularly for client-server web applications, with a reported 9 million developers.
                Java was originally developed by James Gosling at Sun Microsystems (which has since been acquired by Oracle) and released in 1995 as a core component of Sun Microsystems' Java platform. The original and reference implementation Java compilers, virtual machines, and class libraries were originally released by Sun under proprietary licenses. As of May 2007, in compliance with the specifications of the Java Community Process, Sun had relicensed most of its Java technologies under the GNU General Public License.
                 Meanwhile, others have developed alternative implementations of these Sun technologies, such as the GNU Compiler for Java (bytecode compiler), GNU Classpath (standard libraries), and IcedTea-Web (browser plugin for applets).
            """.trimIndent(),
            "James Gosling\n" +
                    "May 23, 1995\n" +
                    "Multi-Paradigm\n" +
                    ".java, .class, .jar\n"
        ),
        arrayOf(
            "Bash/PowerShell",
            "Most Popular Programming Language by StackOverFlow, \nPopular rank 6",
            "https://upload.wikimedia.org/wikipedia/commons/a/af/PowerShell_Core_6.0_icon.png",
            """
                PowerShell is a task automation and configuration management framework from Microsoft, consisting of a command-line shell and associated scripting language. Initially a Windows component only, known as Windows PowerShell, it was made open-source and cross-platform on 18 August 2016 with the introduction of PowerShell Core.[5] The former is built on the .NET Framework, the latter on .NET Core.
                PowerShell, administrative tasks are generally performed by cmdlets (pronounced command-lets), which are specialized .NET classes implementing a particular operation. These work by accessing data in different data stores, like the file system or registry, which are made available to PowerShell via providers. Third-party developers can add cmdlets and providers to PowerShell.[6][7] Cmdlets may be used by scripts and scripts may be packaged into modules.
                PowerShell provides full access to COM and WMI, enabling administrators to perform administrative tasks on both local and remote Windows systems as well as WS-Management and CIM enabling management of remote Linux systems and network devices. PowerShell also provides a hosting API with which the PowerShell runtime can be embedded inside other applications. These applications can then use PowerShell functionality to implement certain operations, including those exposed via the graphical interface.
            """.trimIndent(),
            "Jeffrey Snover\n" +
                    "Nov 14, 2006\n" +
                    "Imperative, pipeline, reflective\n" +
                    ".ps1, .psd1\n"
        ),
        arrayOf(
            "C#",
            "Most Popular Programming Language by StackOverFlow, \nPopular rank 7",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7a/C_Sharp_logo.svg/455px-C_Sharp_logo.svg.png",
            """
                C# (pronounced see sharp, like the musical note C♯, but written with the number sign)[b] is a general-purpose, multi-paradigm programming language encompassing strong typing, lexically scoped, imperative, declarative, functional, generic, object-oriented (class-based), and component-oriented programming disciplines.
                It was developed around 2000 by Microsoft as part of its .NET initiative and later approved as an international standard by Ecma (ECMA-334) in 2002 and ISO (ISO/IEC 23270) in 2003. Mono is the name of the free and open-source project to develop a compiler and runtime for the language. C# is one of the programming languages designed for the Common Language Infrastructure (CLI).
                C# was designed by Anders Hejlsberg, and its development team is currently led by Mads Torgersen. The most recent version is 8.0, which was released in 2019 alongside Visual Studio 2019 version 16.3.
            """.trimIndent(),
            "Microsoft\n" +
                    "2000\n" +
                    "Procedural & Object-Oriented\n" +
                    ".cs\n"
        ),
        arrayOf(
            "PHP",
            "Most Popular Programming Language by StackOverFlow, \nPopular rank 8",
            "https://seeklogo.com/images/P/PHP-logo-0B2FDC4529-seeklogo.com.png",
            """
                PHP is a popular general-purpose scripting language that is especially suited to web development. It was originally created by Danish-Canadian programmer Rasmus Lerdorf in 1994, the PHP reference implementation is now produced by The PHP Group.[7] PHP originally stood for Personal Home Page, but it now stands for the recursive initialism PHP: Hypertext Preprocessor.
                The standard PHP interpreter, powered by the Zend Engine, is free software released under the PHP License. PHP has been widely ported and can be deployed on most web servers on almost every operating system and platform, free of charge.
                The PHP language evolved without a written formal specification or standard until 2014, with the original implementation acting as the de facto standard which other implementations aimed to follow. Since 2014, work has gone on to create a formal PHP specification.
            """.trimIndent(),
            "Rasmus Lerdorf\n" +
                    "1995\n" +
                    "Procedural & Object-Oriented\n" +
                    ".php\n"
        ),
        arrayOf(
            "C++",
            "Most Popular Programming Language by StackOverFlow, \nPopular rank 9",
            "https://seeklogo.com/images/C/c-logo-43CE78FF9C-seeklogo.com.png",
            """
                C++ (/ˌsiːˌplʌsˈplʌs/) is a general-purpose programming language created by Bjarne Stroustrup as an extension of the C programming language, or "C with Classes". The language has expanded significantly over time, and modern C++ now has object-oriented, generic, and functional features in addition to facilities for low-level memory manipulation.
                It is almost always implemented as a compiled language, and many vendors provide C++ compilers, including the Free Software Foundation, LLVM, Microsoft, Intel, Oracle, and IBM, so it is available on many platforms.
                C++ was designed with a bias toward system programming and embedded, resource-constrained software and large systems, with performance, efficiency, and flexibility of use as its design highlights.\
                C++ has also been found useful in many other contexts, with key strengths being software infrastructure and resource-constrained applications,[10] including desktop applications, video games, servers (e.g. e-commerce, Web search, or SQL servers), and performance-critical applications (e.g. telephone switches or space probes).
            """.trimIndent(),
            "Bjarne Stroustrup\n" +
                    "1985\n" +
                    "Multi-Paradigm\n"+
                    ".cpp\n"
        ),
        arrayOf(
            "TypeScript",
            "Most Popular Programming Language by StackOverFlow, \nPopular rank 10",
            "https://seeklogo.com/images/T/typescript-logo-B29A3F462D-seeklogo.com.png",
            """
                TypeScript is an open-source programming language developed and maintained by Microsoft. It is a strict syntactical superset of JavaScript and adds optional static typing to the language. TypeScript is designed for development of large applications and transcompiles to JavaScript.[4] As TypeScript is a superset of JavaScript, existing JavaScript programs are also valid TypeScript programs.
                TypeScript may be used to develop JavaScript applications for both client-side and server-side execution (as with Node.js or Deno). There are multiple options available for transcompilation. Either the default TypeScript Checker can be used,[5] or the Babel compiler can be invoked to convert TypeScript to JavaScript.
            """.trimIndent(),
            "Microsoft\n" +
                    "1 October 2012\n" +
                    "Multi-Paradigm\n" +
                    ".ts, .tsx\n"
        ),
        arrayOf(
            "C",
            "Most Popular Programming Language by StackOverFlow, \nPopular rank 11",
            "https://seeklogo.com/images/C/c-programming-language-logo-9B32D017B1-seeklogo.com.png",
            """
                C (/siː/, as in the letter c) is a general-purpose, procedural computer programming language supporting structured programming, lexical variable scope, and recursion, with a static type system. By design, C provides constructs that map efficiently to typical machine instructions. It has found lasting use in applications previously coded in assembly language. Such applications include operating systems and various application software for computers architectures that range from supercomputers to PLCs and embedded systems.
                A successor to the programming language B, C was originally developed at Bell Labs by Dennis Ritchie between 1972 and 1973 to construct utilities running on Unix. It was applied to re-implementing the kernel of the Unix operating system.[5] During the 1980s, C gradually gained popularity. It has become one of the most widely used programming languages,[6][7] with C compilers from various vendors available for the majority of existing computer architectures and operating systems.
                C has been standardized by the ANSI since 1989 (ANSI C) and by the International Organization for Standardization (ISO).
            """.trimIndent(),
            "Dennis Ritchie\n" +
                    "1972\n" +
                    "structured & procedural\n" +
                    ".c\n"
        ),
        arrayOf(
            "Ruby",
            "Most Popular Programming Language by StackOverFlow, \nPopular rank 12",
            "https://www.pngitem.com/pimgs/m/12-120179_best-free-ruby-png-ruby-programming-language-logo.png",
            """
                Ruby is an interpreted, high-level, general-purpose programming language. It was designed and developed in the mid-1990s by Yukihiro "Matz" Matsumoto in Japan.
                Ruby is dynamically typed and uses garbage collection. It supports multiple programming paradigms, including procedural, object-oriented, and functional programming. According to the creator, Ruby was influenced by Perl, Smalltalk, Eiffel, Ada, Basic, and Lisp
            """.trimIndent(),
            "Yukihiro Matsumoto\n" +
                    "1995\n" +
                    "Multi-Paradigm\n" +
                    ".rb\n"
        )

    )

    val listData: ArrayList<Language>
        get() {
            val list = ArrayList<Language>()
            for (aData in data) {
                val lang = Language()
                lang.name = aData[0]
                lang.rank = aData[1]
                lang.photo = aData[2]
                lang.overview = aData[3]
                lang.detail = aData[4]

                list.add(lang)
            }
            return list
        }
}