# Command Line Utility <a name="about-project"></a>

> **Command Line Utility** allows users to sort input .txt files into three separate files. Each output file contains data of only one type: integers, floats or strings.

## Built With <a name="built-with"></a>

  <ul>
    <li>Java 21</li>
    <li>Maven 3.9.2</li>
    <li>Library: Apache Commons CLI 1.9.0 https://mvnrepository.com/artifact/commons-cli/commons-cli/1.9.0</li>
  </ul>
  
## Usage <a name="usage"></a> 

### Available Options For Sorting  <a name="options"></a>
 <pre><b>-a</b>              Add information to existing files instead of overwriting</pre></li>
 <pre><b>-f</b>              Show full statistics</pre>
 <pre><b>-h, --help</b>      Show utility usage info</pre>
 <pre><b>-o (arg)</b>        Set the filepath for result files</pre>
 <pre><b>-p (arg)</b>        Set the prefix for filenames</pre>
 <pre><b>-s</b>              Show short statistics</pre>

 You can see these options by using flag -h or --help (without any other flags or arguments)
### Usage Instructions ###
<ol>
  <li>
    Download <a href="https://github.com/funchose/command-line-utility/blob/master/out/artifacts/CmdUtility/CmdUtility.jar">CmdUtility.jar</a>
  </li>
  <li>
    For more convenient use, start the terminal from the folder with the CmdUtility.jar
  </li>
  <li>
    Example of command: java -jar CmdUtility.jar -a -f -o C:/Users/User/OutputFolder -p new_ file1.txt file2.txt
  </li>
</ol>

### Implementation Details ###
<ul>
  <li>
    Statistics is collected only for newly recorded information (not for the existing in output files information before the utility launch)
  </li>
  <li>
     A line break is also considered a line
  </li>
</ul>
  
### Usage Advices ###
<ul>
  <li>
    Recommended JDK version: JDK21
  </li>
  <li>
    It's preferable to use the absolute path to source and output files
  </li>
  <li>
    Write float values in the source files with ".", not ",". You can use the exponential form, e.g. 1E5
  </li>
  <li>
    Write integer values in the source files without space symbols, dots, commas or underscores
  </li>
  <li>
    Maximum value for float number is 1E308
  </li>
  <li>
    Maximum value for integer number is 4294967296. After an overflow it is parsed as a float number 
  </li>
  <li>
    Don't forget to put \ or / after "Disk:" when using the absolute path
  </li>
  <li>
    In the "-a" mode, already existing in the output files information will not be overwritten or considered in the statistics
  </li>
  <li>
    Usage of quotes with arguments and filepaths is not prohibited
  </li>
</ul>

## Authors <a name="authors"></a>

 **Anna Popova**

- GitHub: [funchose](https://github.com/funchose)
- Telegram @funch0se
