#!/usr/bin/ruby

module InsertDebugCode
	#For each of the correct files in the template, insert the debug line if it's not already there
	def InsertDebugCode.modifytemplates(dir, language)
		comment = ""
		case language
		when "java", "cpp", "php"
			comment = "//"
		when "ruby"
			comment = "#"
		end
		
		files = getfiles(dir)
		files.each do |file|	
			insertcode("<% if (model.isDebugMode()) { append(stringBuffer, NL + \"" + comment + "Generated by " + dir + file + "\" + NL); } %>" , dir + file)
		end
		
	end

	#insert the code into the file
	def InsertDebugCode.insertcode(code, filename)	
		file = File.open(filename, "r")
		line = file.gets
		if line.chomp == code #Make sure the line doesn't always exist
			file.close;
		else
			puts("Modifying " + filename + "\n")
			temp = File.open(filename + "_temp", "w")
			temp.puts(code)
			temp.puts(line)
			while (line = file.gets)
				temp.puts(line)
			end
			file.close
			temp.close
			File.delete(filename)
			File.rename(filename + "_temp", filename)
		end
	end

	#get a list of each jet and jumpjet file in the directory
	def InsertDebugCode.getfiles(dirname)
		files = []
		Dir.foreach(dirname) do |filename|
			if filename.end_with?(".jet") or filename.end_with?(".jumpjet")
				files << filename
			end
		end
		files
	end
end